package com.post.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
//import java.util.function.Predicate;
import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;








import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.serializer.SerializationException;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.pfpj.bcc.base.teller.api.TellerApi;
import com.pfpj.bcc.base.teller.api.model.Teller;
import com.pfpj.bcc.res.api.menu.MenuApi;
import com.pfpj.bcc.res.api.menu.model.MenuResource;
import com.pfpj.cert.api.LoginProviderApi;
import com.pfpj.common.util.ListUtils;
import com.pfpj.common.util.ListUtilsHook;
import com.post.kfcs.im.itemsM.api.ItemsManagerApi;
import com.post.kfcs.im.itemsM.api.model.ItemsInfos;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chinapost.common.msg.ObjectRestResponse;
import com.chinapost.common.msg.TableResultResponse;
import com.chinapost.common.util.Constants2;
import com.eos.access.http.IWebInterceptor;
import com.eos.access.http.IWebInterceptorChain;
import com.eos.data.datacontext.UserObject;
import com.eos.foundation.common.utils.StringUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.spring.BeanFactory;
import com.eos.system.logging.Logger;

/**
 * 服务调用拦截器
 * 
 * @author Administrator
 * 
 */
public class LoginBuildPowerFilter implements IWebInterceptor {
	private static Logger logger = TraceLoggerFactory
			.getLogger(LoginBuildPowerFilter.class);
	private static final String SECRET = "bccommon:secret";// token 标签
	private JedisUtil jedisUtil;

	/**
	 * 1.验证用户是否存在--从redis服务器中拉取用户用户数据进行验证 2.验证该用户是否有权限访问该URL 3.token是否过半
	 */
	public void doIntercept(HttpServletRequest request,
			HttpServletResponse response, IWebInterceptorChain chain)
			throws IOException, ServletException {
		logger.info("=========LoginBuildPowerFilter  start=======");
		String logfilterFlag = ConfigurationUtil.getUserConfigSingleValue(
				"Logfilter", "LogfilterFlag", "IsOpen");// LogfilterFlag
		if (!"true".endsWith(logfilterFlag)) {
			chain.doIntercept(request, response);
			logger.info("=========LoginBuildPowerFilter  logfilterFlag!=true filter not work=======");
			return;
		}
		// 获取token信息
		String token = request.getHeader("X-ER-UAT");
		HttpSession session = request.getSession();
		BeanFactory beanFactory = BeanFactory.newInstance();
		boolean ret = false;
		jedisUtil = JedisUtil.getInstance();
		// 判断token是否已存在
		if (jedisUtil.exists("logOutToken:" + token)) {
			response.setStatus(403, "Your Session Has Expired");
		}
		if (!isUrlCommon(request.getRequestURI())) {
			// 进行Token判断
			boolean tokenTimeOutFlag = verifyToken(token);
			if (tokenTimeOutFlag) {
				boolean hasPrimession = getUrlHasPrimession(
						request.getRequestURI(), token, request.getMethod());
				if (!hasPrimession) {
					response.setStatus(401,
							"You Have No Primession To Visit This API.");
					logger.error("You Have No Primession To Visit This API.");
					return;
				}
				ret = hasPrimession;
			} else {
				response.setStatus(403, "Your Session Has Expired.");
				logger.error("LoginBuildPowerFilter  " + token
						+ "  Your Session Has Expired.");
				return;
			}
		} else {
			ret = true;
		}
		String result = token;
		if (!StringUtil.isBlank(result)) {
			DecodedJWT jwt = parseToken(token);
			Date expireAt = jwt.getExpiresAt();
			Date issuedAt = jwt.getIssuedAt();
			// token 过半刷新
			if (expireAt.getTime() - issuedAt.getTime() / 2 <= System
					.currentTimeMillis() - issuedAt.getTime()) {
//				BeanFactory beanFactory = BeanFactory.newInstance();
				LoginProviderApi loginProviderApi = beanFactory
						.getBean("loginProviderApi");
				ObjectRestResponse<String> results = loginProviderApi
						.createToken(token);
				if (Constants2.FEIGN_REQUEST_SUCCESS.equals(results.getCode())) {
					result = results.getData();
				}
			}
		}
		if (token != null) {
			Map<String, Object> info = (Map<String, Object>) jedisUtil
					.get("onlineUser:" + result);
			logger.info("=========LoginBuildPowerFilter  onlineUser.info ======="+info.toString());
			if (info != null) {
				UserObject userObject = new UserObject();
				userObject.setUserId((String) info.get("userId"));
				userObject.setUserName((String) info.get("userName"));
				userObject.setUserOrgId((String) info.get("groupId"));
				//获取用户基本信息
//				BeanFactory beanFactory = BeanFactory.newInstance();
				TellerApi tellerApi = beanFactory
						.getBean("tellerApi");
				Teller tlr=new Teller();
				tlr.setTlrno((String) info.get("userId"));
				TableResultResponse<Teller> returnTlr=tellerApi.queryTellerInfo(tlr);
				List<Teller> tlrList=returnTlr.getData().getData();
				//获取用户项目信息
//				BeanFactory beanFactory = BeanFactory.newInstance();
				ItemsManagerApi itemsApi = beanFactory
						.getBean("itemsApi");
				ItemsInfos itemsInfos=new ItemsInfos();
				itemsInfos.setUserId((String) info.get("userId"));
				//TODO 应该传入itemsCd的值 目前先传空值
//				itemsInfos.setItemsCd();
				TableResultResponse<ItemsInfos> returnItems=itemsApi.queryItemsInfoAndUser(itemsInfos);
				List<ItemsInfos> itemsList=returnItems.getData().getData();
				Map<String,Object> tlrInfoMap=new HashMap<String,Object>();
				tlrInfoMap.put("tlrInfo", tlrList.get(0));//人员基本信息
				tlrInfoMap.put("itemsInfo",itemsList.get(0));//人员项目信息
				userObject.setAttributes(tlrInfoMap);
				session.setAttribute("userObject", userObject);
				session.setAttribute("info", info);
			}
		}
		response.setHeader("X-ER-UAT", result);
		
		//获取是否发送消息标识
		String SendRocketFlag = ConfigurationUtil.getUserConfigSingleValue(
				"Logfilter", "LogfilterFlag", "SendRocketFlag");// SendRocketFlag
		if("true".endsWith(SendRocketFlag)){
			// 发送rocketmq消息
			sendMessage(request, response);
		}
		if (ret) {
			logger.info("=========LoginBuildPowerFilter  end=======");
			// 执行后续拦截器
			chain.doIntercept(request, response);
		}
	}

	/**
	 * URL权限校验
	 * 
	 * @param requestUri
	 * @param token
	 * @param method
	 * @return
	 */
	private boolean getUrlHasPrimession(final String requestUri, String token,
			final String method) {
//		return true;
		
		//测试期间先关闭此方法，打版的时候需要放开此方法
		
		List<MenuResource> allMenuResource = getAllUrls();

		// 存放过滤结果的列表
        List<MenuResource> result = null;
        result = ListUtils.filter(allMenuResource, new ListUtilsHook<MenuResource>(){
            public boolean test(MenuResource resource) {
            	String url = resource.getFuncAction();
				String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
				String regEx = "^" + uri + "$";
				return (Pattern.compile(regEx).matcher(requestUri).find()
						|| requestUri.startsWith(url + "/") && method.equals(resource.getMethodType()));
            }
        });
		if (result.isEmpty()) {
			return true;
		} else {
			return checkCurrentPrimessionAndUrl(requestUri, method, token);
		}
	}

	/**
	 * 
	 * @param requestUri
	 * @return
	 */

	private boolean isUrlCommon(String requestUri) {
		boolean flag = false;
		List<String> commonUrls = null;
		try {
			commonUrls = (List<String>) jedisUtil
					.get("bccommon:commonUrl:start");
		} catch (SerializationException e) {
			logger.error("LoginBuildPowerFilter  isUrlCommon SerializationException"
					+ e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("LoginBuildPowerFilter  isUrlCommon UnsupportedEncodingException"
					+ e.getMessage());
		}
		if (commonUrls != null && commonUrls.size() > 0) {
			for (String s : commonUrls) {
				if (requestUri.endsWith(s)) {
					return true;
				}
			}
		}
		return flag;
	}

	/**
	 * 进行Token判断
	 * 
	 * @param token
	 * @return
	 */
	private boolean verifyToken(String token) {
		try {
			String secret = (String) jedisUtil.get(SECRET);

			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
					.build();
			DecodedJWT jwt = verifier.verify(token);
			if (jwt != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.info("登陆凭证已经过期，请重新登陆{}", e);
			return false;
		}
	}

	/**
	 * 解析token
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IllegalArgumentException
	 */
	public DecodedJWT parseToken(String token)
			throws UnsupportedEncodingException {
		// String secret = (String) serializer.deserialize(jedisStringUtil.get(
		// SECRET).getBytes("UTF-8"));

		String secret = (String) jedisUtil.get(SECRET);

		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
		DecodedJWT jwt;
		jwt = verifier.verify(token);
		return jwt;
	}

	/**
	 * 获取所有配置权限的URL
	 */
	private List<MenuResource> getAllUrls() {
		List<MenuResource> allMenuResource = null;
		try {
			// String result = jedisStringUtil
			// .get("bcc:menuresource:allmenuresource");
			// if (!StringUtil.isBlank(result)) {
			// allMenuResource = (List<MenuResource>) serializer
			// .deserialize(result.getBytes("UTF-8"));
			// }
			allMenuResource = (List<MenuResource>) jedisUtil
					.get("bcc:menuresource:allmenuresource");
		} catch (SerializationException e) {
			logger.error("LoginBuildPowerFilter  getAllUrls SerializationException"
					+ e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("LoginBuildPowerFilter  getAllUrls UnsupportedEncodingException"
					+ e.getMessage());
		}
		if (allMenuResource == null || allMenuResource.isEmpty()) {
			BeanFactory beanFactory = BeanFactory.newInstance();
			MenuApi menuApi = beanFactory.getBean("menuApi");
			com.chinapost.common.msg.ListRestResponse<MenuResource> resources = menuApi
					.menuResourceList(new MenuResource());
			return resources.getData();
		} else {
			return allMenuResource;
		}
	}

	/**
	 * 检查URL权限
	 * 
	 * @param requestUri
	 * @param method
	 * @param token
	 * @return
	 */
	private boolean checkCurrentPrimessionAndUrl(final String requestUri,
			final String method, String token) {
		List<String> currentMenuResource = getUserPrimession(token);
		List<String> result = null;
        result = ListUtils.filter(currentMenuResource, new ListUtilsHook<String>(){
            public boolean test(String resource) {
            	String url = resource;
				String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
				String regEx = "^" + uri + "$";
				return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"));
            }
        });
		return !result.isEmpty();
	}

	private List<String> getUserPrimession(String token) {
		HashMap userInfo = null;
		try {

			// String result = jedisStringUtil
			// .get("bcc:menuresource:allmenuresource");
			// if (!StringUtil.isBlank(result)) {
			// userInfo = (HashMap) serializer.deserialize(result
			// .getBytes("UTF-8"));
			// }
			userInfo = (HashMap) jedisUtil
					.get("bcc:menuresource:allmenuresource");
			// userInfo = (HashMap)serializer
			// .deserialize(jedisStringUtil.get(
			// "bcc:menuresource:allmenuresource").getBytes(
			// "UTF-8"));
		} catch (SerializationException e) {
			logger.error("LoginBuildPowerFilter  getUserPrimession SerializationException"
					+ e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("LoginBuildPowerFilter  getUserPrimession UnsupportedEncodingException"
					+ e.getMessage());
		}
		if (userInfo != null) {
			return (List<String>) userInfo.get("urls");
		} else {
			return new ArrayList<String>();
		}
	}

	/**
	 * 发送消息
	 */
	private void sendMessage(HttpServletRequest request,
			HttpServletResponse response) {
		String url = request.getRequestURL().toString();
		String serverIp = ConfigurationUtil.getUserConfigSingleValue(
				"Logfilter", "RocketServer", "ServerIp");// RocketServer
		String topic = ConfigurationUtil.getUserConfigSingleValue("Logfilter",
				"RocketServer", "Topic");// RocketServer
		String tag = ConfigurationUtil.getUserConfigSingleValue("Logfilter",
				"RocketServer", "Tag");// RocketServer
		String producerId = ConfigurationUtil.getUserConfigSingleValue(
				"Logfilter", "RocketServer", "ProducerId");// ProducerId
		String accessKey = ConfigurationUtil.getUserConfigSingleValue(
				"Logfilter", "RocketServer", "AccessKey");// AccessKey
		String secretKey = ConfigurationUtil.getUserConfigSingleValue(
				"Logfilter", "RocketServer", "SecretKey");// SecretKey

		String path = request.getContextPath();// 应用名称
		UserObject userObject = (UserObject) request.getSession().getAttribute(
				"userObject");

		String userId = userObject.getUserId();
		// 客户端IP地址
		String ip = request.getRemoteAddr();
		// 录入参数
		Map param = request.getParameterMap();
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap.put("ip", ip);// IP
		sendMap.put("url", url);// URL
		sendMap.put("userId", userId);// 用户ID
		sendMap.put("param", param);// 参数
		sendMap.put("timestamp", new Date());// 日期
		sendMap.put("centerName", path);// 应用中心名称

		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId, producerId);
		properties.put(PropertyKeyConst.AccessKey, accessKey);
		properties.put(PropertyKeyConst.SecretKey,secretKey);
		properties.put(PropertyKeyConst.ONSAddr, serverIp);// 
		Producer producer = ONSFactory.createProducer(properties);

		producer.start();
		Message msg = new Message(topic,// topic
				tag,// tag
				JSONObject.toJSON(sendMap).toString().getBytes());// body
		logger.info("sendMessage" + JSONObject.toJSON(sendMap).toString());
		producer.send(msg);
		producer.shutdown();
	}
}
