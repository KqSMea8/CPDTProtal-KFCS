package org.gocom.components.coframe.auth.login;
import java.io.*;
import java.util.*;
import java.math.*;
import commonj.sdo.DataObject;
import com.primeton.engine.core.impl.*;
import com.primeton.engine.*;
import com.primeton.ext.engine.component.Operation;
import com.primeton.ext.engine.component.asyn.AsynInvokeBiz;
import com.primeton.ext.engine.component.asyn.AsynInvokeService;
import com.eos.engine.component.ILogicComponent;
import com.primeton.ext.engine.component.LogicComponentFactory;
import com.eos.common.transaction.ITransactionDefinition;
import com.primeton.ext.data.sdo.DataUtil;
import com.eos.data.datacontext.DataContextManager;
import com.eos.common.transaction.ITransactionDefinition;
import com.eos.common.asyn.AsynMethodTemplateFactory;
import com.eos.common.asyn.AsynMethodTemplate;
import com.primeton.engine.core.impl.helper.DataBuilder;
import com.eos.runtime.resource.ContributionMetaData;
import com.primeton.ext.runtime.resource.ContributionMetaDataManager;
import com.primeton.engine.core.impl.context.RuntimeContext;
import com.primeton.ext.engine.core.ExceptionConstant;
import com.primeton.engine.core.exception.EOSEngineRuntimeException;
import com.primeton.ext.engine.core.ExceptionConstant;
import com.eos.access.client.ServiceContext;
import com.eos.sca.host.IServiceInvoker;
import com.eos.engine.core.ValidateModel;
import com.eos.engine.core.ValidateResult;
import com.eos.engine.core.mbean.EngineConfigHandler;
import com.eos.engine.core.mbean.EngineConfigModel;
import com.primeton.ext.common.logging.LoggerFactory;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;
import com.primeton.ext.common.logging.SysLogger;
import com.eos.common.statistic.StatisticManager;
import com.eos.common.statistic.StatisticMessage;
import com.primeton.ext.common.statistic.BizflowStatisticMessage;
import com.primeton.ext.common.statistic.PageflowStatisticMessage;
import com.primeton.ext.common.statistic.ServiceStatisticMessage;
import com.primeton.ext.common.statistic.SQLStatisticMessage;
import com.eos.data.datacontext.ISessionMap;
import com.eos.data.datacontext.IMUODataContext;
import com.eos.data.datacontext.IUserObject;
import com.eos.system.ServerContext;
import com.primeton.ext.engine.core.ValidateMessage;
import com.primeton.ext.system.utility.international.ResourceMessageUtil;
import com.eos.data.datacontext.IMUODataContext;
import com.primeton.ext.common.muo.MUODataContextHelper;
import com.eos.data.datacontext.IMapContextFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.primeton.engine.core.impl.context.PageflowRuntimeContext;
import com.eos.data.datacontext.IDataContext;
import javax.servlet.ServletException;
public class login_flow extends com.primeton.engine.core.impl.AbstractJavaPageflow{
com.eos.data.datacontext.IDataContext sessionContext = DataContextManager.current().getSessionCtx();
int result;
public String getQName(){
  return "org.gocom.components.coframe.auth.login.login.flow";
}
public String getShortName(){
  return "login.flowx";
}
public List<Activity> getActivities(){
  if(this._eosInternal.activities==null){
    this._eosInternal.activities = new ArrayList<Activity>();
    this._eosInternal.activities.add(new Activity("start0","开始","start"));
    this._eosInternal.activities.add(new Activity("end0","登录界面","end"));
    this._eosInternal.activities.add(new Activity("subprocess0","验证登录","subprocess"));
    this._eosInternal.activities.add(new Activity("end1","主页","end"));
  }
  return this._eosInternal.activities;
}
public String getProcessName(){
  return "";
}
private int subprocess0(RuntimeContext __context,java.lang.String userId,java.lang.String password,java.lang.String original_url) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("subprocess0");
__context.setCurrentActivityName("验证登录");
__context.setCurrentActivityType("subprocess");
__context.setCurrentActivityValue("org.gocom.components.coframe.auth.LoginManager.login");
this.beforeHandlers("subprocess0",__context);
  IMUODataContext muo = MUODataContextHelper.create(sessionContext);
  IMapContextFactory mapContextFactory = MUODataContextHelper.bind(muo);
TransactionManagerProxy txProxy = this._eosInternal.getTxProxy();
boolean __oldStatus = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
  if(txProxy.getStatus() != com.eos.common.transaction.ITransactionManager.Status.STATUS_NO_TRANSACTION){
    com.primeton.ext.access.ejb.jta.JtaContextManager.current().setWithinJta(true);
  }
}catch(Throwable _t){}
    String __appName = "";
    if(!"".equals(__appName)){
      __appName = __appName+"$";
    }
    String __componentName = "org.gocom.components.coframe.auth.LoginManager";
    String __optName = "login";
try{
    ILogicComponent __component = new ServiceContext().locateBizLogic(__appName+__componentName);
  Object[] __params =  new Object[2];
__params[0] = com.primeton.ext.data.sdo.DataUtil.toString(userId);
__params[1] = com.primeton.ext.data.sdo.DataUtil.toString(password);
	  Object[]___return = __component.invoke(__optName, __params,"null");
    __context.setInt("result",___return[0]);
    this.result=__context.getInt("result");
}catch(com.primeton.engine.core.exception.EOSEngineRuntimeException __ex){
  if(__ex.getCause()!=null){
    throw __ex.getCause();
  }else{
    throw __ex;
  }}finally{
    MUODataContextHelper.flush(muo, sessionContext);
    MUODataContextHelper.unbind(mapContextFactory);
com.primeton.ext.access.ejb.jta.JtaContextManager.current().setWithinJta(__oldStatus);
}
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(result,1,"EQ")){
    __eosCurrentActivityID=40001;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=40000;
  this.afterHandlers("subprocess0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("subprocess0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=验证登录][activity id=subprocess0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("subprocess0",__context);
}
}
private int end0(PageflowRuntimeContext __context,HttpServletRequest request,HttpServletResponse response,java.lang.String userId,java.lang.String password,java.lang.String original_url) throws Throwable{
int __eosCurrentActivityID=-1;
try{
__context.setCurrentActivityID("end0");
__context.setCurrentActivityName("登录界面");
__context.setCurrentActivityType("end");
__context.setCurrentActivityValue(null);
this.beforeHandlers("end0",__context);
  setFinished();
  this.setCurrentView("end0");
  if(this.getFlowContext().get("_eosParentFlowRuntimeContext")!=null){
    __eosCurrentActivityID=-1;
    List<String> __remainList = new ArrayList<String>();
    __context.deleteAllObject(__remainList);
    this.afterHandlers("end0",__context);
    return __eosCurrentActivityID;
  }
  if(__context.isReturnXML()){
    sendXMLResponse(response,__context);
    this.afterHandlers("end0",__context);
    return -1;
  }
  ContributionMetaData currentContext = null;
  ContributionMetaData contributionContext = null;
  ContributionMetaData[] contributionContexts = ContributionMetaDataManager.getContributionMetaByModel("_web/coframe/auth/login/login.jsp");
  if(contributionContexts != null && contributionContexts.length>0){
    contributionContext = contributionContexts[0];
  }
  try{
    currentContext = DataContextManager.current().getContributionMetaData();
    DataContextManager.current().pushContributionMetaData(contributionContext);
  String __uri = "/coframe/auth/login/login.jsp";
  if(__uri == null || __uri.length()<1){
    __uri = EngineConfigHandler.getEngineConfigModel().getEndPage();
  }
  request.setAttribute("userId",userId);
  request.setAttribute("password",password);
  request.setAttribute("original_url",original_url);
  String[] __rootNames = __context.getRootEntryNames();
  for(int __i=0;__i<__rootNames.length;__i++){
  	if(request.getAttribute(__rootNames[__i])==null){
  		request.setAttribute(__rootNames[__i],__context.get(__rootNames[__i]));
     }
  }
  saveHistory2Client(request);
  writeFlowKeyNull(request);
  writeLastAccessAction(request);
  sendView(request,response,__uri);
  }finally{
  	DataContextManager.current().popContributionMetaData();
  }
  __eosCurrentActivityID=-1;
  this.afterHandlers("end0",__context);
  return __eosCurrentActivityID;
}catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("end0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=登录界面][activity id=end0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("end0",__context);
}
}
private int end1(PageflowRuntimeContext __context,HttpServletRequest request,HttpServletResponse response,java.lang.String userId,java.lang.String password,java.lang.String original_url) throws Throwable{
int __eosCurrentActivityID=-1;
try{
__context.setCurrentActivityID("end1");
__context.setCurrentActivityName("主页");
__context.setCurrentActivityType("end");
__context.setCurrentActivityValue(null);
this.beforeHandlers("end1",__context);
  setFinished();
  this.setCurrentView("end1");
  if(this.getFlowContext().get("_eosParentFlowRuntimeContext")!=null){
    __eosCurrentActivityID=-1;
    List<String> __remainList = new ArrayList<String>();
    __context.deleteAllObject(__remainList);
    this.afterHandlers("end1",__context);
    return __eosCurrentActivityID;
  }
  if(__context.isReturnXML()){
    sendXMLResponse(response,__context);
    this.afterHandlers("end1",__context);
    return -1;
  }
  ContributionMetaData currentContext = null;
  ContributionMetaData contributionContext = null;
  ContributionMetaData[] contributionContexts = ContributionMetaDataManager.getContributionMetaByModel("_web/coframe/auth/login/redirect.jsp");
  if(contributionContexts != null && contributionContexts.length>0){
    contributionContext = contributionContexts[0];
  }
  try{
    currentContext = DataContextManager.current().getContributionMetaData();
    DataContextManager.current().pushContributionMetaData(contributionContext);
  String __uri = "/coframe/auth/login/redirect.jsp";
  if(__uri == null || __uri.length()<1){
    __uri = EngineConfigHandler.getEngineConfigModel().getEndPage();
  }
  request.setAttribute("userId",userId);
  request.setAttribute("password",password);
  request.setAttribute("original_url",original_url);
  String[] __rootNames = __context.getRootEntryNames();
  for(int __i=0;__i<__rootNames.length;__i++){
  	if(request.getAttribute(__rootNames[__i])==null){
  		request.setAttribute(__rootNames[__i],__context.get(__rootNames[__i]));
     }
  }
  saveHistory2Client(request);
  writeFlowKeyNull(request);
  writeLastAccessAction(request);
  sendView(request,response,__uri);
  }finally{
  	DataContextManager.current().popContributionMetaData();
  }
  __eosCurrentActivityID=-1;
  this.afterHandlers("end1",__context);
  return __eosCurrentActivityID;
}catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("end1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=主页][activity id=end1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("end1",__context);
}
}
private String _action0(PageflowRuntimeContext __context,HttpServletRequest request,HttpServletResponse response)throws Throwable{
  String nextViewName=null;
  java.lang.String  userId=null;
  java.lang.String  password=null;
  java.lang.String  original_url=null;
  userId=  (java.lang.String)com.primeton.engine.core.impl.helper.DataBuilder.toString("userId",__context);
  password=  (java.lang.String)com.primeton.engine.core.impl.helper.DataBuilder.toString("password",__context);
  original_url=  (java.lang.String)com.primeton.engine.core.impl.helper.DataBuilder.toString("original_url",__context);
  int __eosCurrentActivityID=190000;//start0//
  while(__eosCurrentActivityID>=0){//1,2//
    switch(__eosCurrentActivityID){
    case 190000:
      __eosCurrentActivityID = subprocess0(__context,userId,password,original_url);//subprocess0,5//
      userId=(java.lang.String)__context.getString("userId");
      password=(java.lang.String)__context.getString("password");
      original_url=(java.lang.String)__context.getString("original_url");
      break;
    case 40000:
      nextViewName="end0";//end0,3//
      __eosCurrentActivityID = end0(__context,request,response,userId,password,original_url);
      break;
    case 40001:
      nextViewName="end1";//end1,3//
      __eosCurrentActivityID = end1(__context,request,response,userId,password,original_url);
      break;
    }
  }
  return nextViewName;
}
private String _start0(PageflowRuntimeContext __context,HttpServletRequest request,HttpServletResponse response,String _eosFlowAction)throws Throwable{
  if(_eosFlowAction==null || _eosFlowAction.length()<1){//start0//
    _eosFlowAction="action0";
  }
  if(_eosFlowAction.equalsIgnoreCase("action0")){
      setCurrentAction("action0");
    return  _action0(__context,request,response);//linkstart:link0//
  }
  throw new ServletException("no actionName specified and no default action set either");
}
public  String process(HttpServletRequest request,HttpServletResponse response,String actionName,PageflowRuntimeContext __context)throws Throwable{
  try{
  sessionContext = DataContextManager.current().getSessionCtx();
String __actionName = this.getActionName(null,actionName);
IUserObject __userObject = ((com.eos.data.datacontext.ISessionMap)sessionContext).getUserObject();String __remoteIP = null;if(__userObject != null) __remoteIP = __userObject.getUserRemoteIP();com.eos.common.statistic.StatisticMessage __item=new com.primeton.ext.common.statistic.PageflowStatisticMessage(this.getQName(),DataContextManager.current().getContextStack().getUniqueID(),__actionName,__remoteIP);
if(__userObject != null) __item.setUserId(__userObject.getUserId());
com.eos.common.statistic.StatisticManager.push(__item);
    if(actionName == null || actionName.length()<1 || "action0".equalsIgnoreCase(actionName)){
      setCurrentAction("action0");
      return _action0(__context,request,response);
    }
    return null;
  }finally{
com.eos.common.statistic.StatisticManager.pop();
    __context = null;
  }
}
public  String process(HttpServletRequest request,HttpServletResponse response,String currentViewName,String actionName,PageflowRuntimeContext __context)throws Throwable{
  try{
  sessionContext = DataContextManager.current().getSessionCtx();
String __actionName = this.getActionName(currentViewName,actionName);
IUserObject __userObject = null;if(sessionContext != null)__userObject = ((com.eos.data.datacontext.ISessionMap)sessionContext).getUserObject();String __remoteIP = null;if(__userObject != null) __remoteIP = __userObject.getUserRemoteIP();com.eos.common.statistic.StatisticMessage __item=new com.primeton.ext.common.statistic.PageflowStatisticMessage(this.getQName(),DataContextManager.current().getContextStack().getUniqueID(),__actionName,__remoteIP);
if(__userObject != null) __item.setUserId(__userObject.getUserId());
com.eos.common.statistic.StatisticManager.push(__item);
    if("end1".equalsIgnoreCase(currentViewName)){
      if(isStatefulInstance()){
          return _start0(__context,request,response,null);
      }else{
          if(actionName != null || actionName.length()>0){
             return process(request,response,actionName,__context);
          }else{
              return _start0(__context,request,response,null);
          }
      }
    }
    if(currentViewName == null || "start0".equalsIgnoreCase(currentViewName)){
      return _start0(__context,request,response,actionName);
    }
    if("end0".equalsIgnoreCase(currentViewName)){
      if(isStatefulInstance()){
          return _start0(__context,request,response,null);
      }else{
          if(actionName != null || actionName.length()>0){
             return process(request,response,actionName,__context);
          }else{
              return _start0(__context,request,response,null);
          }
      }
    }
    return null;
  }finally{
    com.eos.common.statistic.StatisticManager.pop();
    __context = null;
  }
}
public boolean isStatefulInstance(){
  return false;
}
public int getTimeout(){
  return 0;
}
public String getHistoryLoc(){
  return "none";
}
protected void initActionVariable(){
      Variable[]__inputVariables = null;
      String name = null; 
      name = "PAGEFLOW_ACTION_VARIABLE_start0_action0"; 
      __inputVariables = new Variable[3];
         __inputVariables[0] = new Variable();
      __inputVariables[0].setArray(false);
      __inputVariables[0].setCategory(1);
      __inputVariables[0].setName("userId");
      __inputVariables[0].setTypeClass(java.lang.String.class);
      __inputVariables[0].setTypeName("java.lang.String");

         __inputVariables[1] = new Variable();
      __inputVariables[1].setArray(false);
      __inputVariables[1].setCategory(1);
      __inputVariables[1].setName("password");
      __inputVariables[1].setTypeClass(java.lang.String.class);
      __inputVariables[1].setTypeName("java.lang.String");

         __inputVariables[2] = new Variable();
      __inputVariables[2].setArray(false);
      __inputVariables[2].setCategory(1);
      __inputVariables[2].setName("original_url");
      __inputVariables[2].setTypeClass(java.lang.String.class);
      __inputVariables[2].setTypeName("java.lang.String");

      this._eosInternal.getAttrs().put("PAGEFLOW_ACTION_VARIABLE_start0_action0", __inputVariables);

      name = "PAGEFLOW_ACTION_VARIABLE_action0"; 
      __inputVariables = new Variable[3];
         __inputVariables[0] = new Variable();
      __inputVariables[0].setArray(false);
      __inputVariables[0].setCategory(1);
      __inputVariables[0].setName("userId");
      __inputVariables[0].setTypeClass(java.lang.String.class);
      __inputVariables[0].setTypeName("java.lang.String");

         __inputVariables[1] = new Variable();
      __inputVariables[1].setArray(false);
      __inputVariables[1].setCategory(1);
      __inputVariables[1].setName("password");
      __inputVariables[1].setTypeClass(java.lang.String.class);
      __inputVariables[1].setTypeName("java.lang.String");

         __inputVariables[2] = new Variable();
      __inputVariables[2].setArray(false);
      __inputVariables[2].setCategory(1);
      __inputVariables[2].setName("original_url");
      __inputVariables[2].setTypeClass(java.lang.String.class);
      __inputVariables[2].setTypeName("java.lang.String");

      this._eosInternal.getAttrs().put("PAGEFLOW_ACTION_VARIABLE_action0", __inputVariables);

}
public Variable[] getFieldVariables(){
    Variable[] __inputVariables = new Variable[1];
     __inputVariables[0] = new Variable();
      __inputVariables[0].setArray(false);
      __inputVariables[0].setCategory(1);
      __inputVariables[0].setName("result");
      __inputVariables[0].setTypeClass(int.class);
      __inputVariables[0].setTypeName("int");
      __inputVariables[0].setHistoryLocation(1);

    return __inputVariables;
}
public login_flow(){
     __flowContextThread.get().set("result",result);
initDefaultActions();
initActionDataConverts();
initAllActions();
initAllActionURLs();
initAllActionView();
}
public void setFlowContext(IDataContext flowContext) {
  super.setFlowContext(flowContext);
  this.result=__flowContextThread.get().getInt("result");
}
public void initDefaultActions(){
 _eosInternal.setDefaultAction("start0","action0");
}
public void initActionDataConverts(){
 _eosInternal.setActionDataConverts("action0",Boolean.FALSE);
}
public String getViewUri(String viewID){
  if(viewID == null){
    return null;
  }
  if(viewID.equals("end1")){
    return "/coframe/auth/login/redirect.jsp";
  }
  if(viewID.equals("end0")){
    return "/coframe/auth/login/login.jsp";
  }
  return null;
}
public String getStartID(){
 return "start0";
}
public void initAllActions(){
    this._eosInternal.allActions.add("action0");
}
public void initAllActionURLs(){
    this._eosInternal.setActionURLs("action0","");
}
public void initAllActionView(){
    this._eosInternal.setActionView("action0","");
}
}
