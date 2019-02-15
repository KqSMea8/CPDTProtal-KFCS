package org.gocom.components.coframe.auth.LoginManager;
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
import com.eos.data.datacontext.UserObject;
public class login_biz extends com.primeton.engine.core.impl.AbstractJavaBL{
com.eos.data.datacontext.IDataContext sessionContext = null;
Object[] __results = new Object[1];;
com.eos.data.datacontext.UserObject userObject= new com.eos.data.datacontext.UserObject();
int retCode;
public login_biz(){
     __context.set("userObject",userObject);
}
public login_biz(java.util.Map head,java.util.Map attachment){
super(head,attachment);
     __context.set("userObject",userObject);
}
public String getQName(){
  return "org.gocom.components.coframe.auth.LoginManager.login.biz";
}
public String getShortName(){
  return "login";
}
public List<Activity> getActivities(){
  if(this._eosInternal.activities==null){
    this._eosInternal.activities = new ArrayList<Activity>();
    this._eosInternal.activities.add(new Activity("start0","开始","start"));
    this._eosInternal.activities.add(new Activity("end0","结束","end"));
    this._eosInternal.activities.add(new Activity("subprocess0","验证用户","subprocess"));
    this._eosInternal.activities.add(new Activity("invokeSpring0","构造登录用户实例","invoke"));
    this._eosInternal.activities.add(new Activity("invokeSpring1","初始化用户","invoke"));
  }
  return this._eosInternal.activities;
}
public String getProcessName(){
  return "";
}
public boolean isAccessOutTransaction(){
  return true;
}
public Variable[] getVariables(String category){
  Variable[]variables = null;
  if(category.equals(Operation.BIZ_INPUT_VARIBLE)){
      variables = (Variable[])this._eosInternal.getAttrs().get(Operation.BIZ_INPUT_VARIBLE);
  }else{
      variables = (Variable[])this._eosInternal.getAttrs().get(Operation.BIZ_OUTPUT_VARIBLE);
  }
  if(variables == null){
      Variable[]__inputVariables = new Variable[2];
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

      Variable[]__outputVariables = new Variable[1];
      __outputVariables[0] = new Variable();
      __outputVariables[0].setArray(false);
      __outputVariables[0].setCategory(1);
      __outputVariables[0].setName("retCode");
      __outputVariables[0].setTypeClass(int.class);
      __outputVariables[0].setTypeName("int");
      this._eosInternal.getAttrs().put(Operation.BIZ_INPUT_VARIBLE, __inputVariables);
      this._eosInternal.getAttrs().put(Operation.BIZ_OUTPUT_VARIBLE, __outputVariables);
      variables = (Variable[])this._eosInternal.getAttrs().get(category);
   }
   return variables;
}
private int start0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("start0");
__context.setCurrentActivityName("开始");
__context.setCurrentActivityType("start");
__context.setCurrentActivityValue(null);
this.beforeHandlers("start0",__context);
  __eosCurrentActivityID=190000;
  this.afterHandlers("start0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("start0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=开始][activity id=start0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("start0",__context);
}
}
private int end0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("end0");
__context.setCurrentActivityName("结束");
__context.setCurrentActivityType("end");
__context.setCurrentActivityValue(null);
this.beforeHandlers("end0",__context);
  __results = new Object[1];
  __results[0]=com.primeton.ext.data.sdo.DataUtil.toInt(this.retCode);
  __eosCurrentActivityID=-1;
  this.afterHandlers("end0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("end0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=结束][activity id=end0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("end0",__context);
}
}
private int subprocess0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("subprocess0");
__context.setCurrentActivityName("验证用户");
__context.setCurrentActivityType("subprocess");
__context.setCurrentActivityValue("this.authentication");
this.beforeHandlers("subprocess0",__context);
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
    String __componentName = "this";
    String __optName = "authentication";
try{
    ILogicComponent __component = (ILogicComponent)this.__component;
  Object[] __params =  new Object[2];
__params[0] = com.primeton.ext.data.sdo.DataUtil.toString(userId);
__params[1] = com.primeton.ext.data.sdo.DataUtil.toString(password);
	  Object[]___return = __component.invoke(__optName, __params,"join");
    __context.setInt("retCode",___return[0]);
    this.retCode=__context.getInt("retCode");
}catch(com.primeton.engine.core.exception.EOSEngineRuntimeException __ex){
  if(__ex.getCause()!=null){
    throw __ex.getCause();
  }else{
    throw __ex;
  }}finally{
com.primeton.ext.access.ejb.jta.JtaContextManager.current().setWithinJta(__oldStatus);
}
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(this.retCode,"1","EQ")){
    __eosCurrentActivityID=400001;
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
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=验证用户][activity id=subprocess0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("subprocess0",__context);
}
}
private int invokeSpring0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring0");
__context.setCurrentActivityName("构造登录用户实例");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring0",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = this.userObject;
Class[]__paramTypes = new Class[1];
__paramTypes[0] = com.eos.data.datacontext.UserObject.class;
com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("LoginServiceBean","login",__paramTypes, __params);

}finally{
}
  __eosCurrentActivityID=40000;
  this.afterHandlers("invokeSpring0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=构造登录用户实例][activity id=invokeSpring0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring0",__context);
}
}
private int invokeSpring1(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring1");
__context.setCurrentActivityName("初始化用户");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring1",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = com.primeton.ext.data.sdo.DataUtil.toString(userId);
Class[]__paramTypes = new Class[1];
__paramTypes[0] = java.lang.String.class;
	  Object __return = com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("LoginServiceBean","initUserObject",__paramTypes, __params);

__context.set("userObject",__return);
    this.userObject=(com.eos.data.datacontext.UserObject)__context.get("userObject");
}finally{
}
  __eosCurrentActivityID=400000;
  this.afterHandlers("invokeSpring1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=初始化用户][activity id=invokeSpring1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring1",__context);
}
}
public Object[] _invoke(java.lang.String userId,java.lang.String password) throws Throwable{
       if(isSuspend()){
           return null;
       }
  __context.set("userId",userId);
  __context.set("password",password);
  __context.set("userObject",userObject);
  __context.set("retCode",retCode);
  __invokeBiz();
  return __results;
}
public Object[] invoke(Object[] __params) throws Throwable{
Logger tracelog = TraceLoggerFactory.getLogger(this.getQName());
Variable[] variables = this.getVariables(Operation.BIZ_INPUT_VARIBLE);
if (__params != null) {
    for(int i = 0; i < __params.length; i++) {
        if(__params[i] == null) {
            tracelog.warn("Warning: [biz={0}][param={1}] is null.", new Object[]{this.getQName(), variables[i].getName()});
        }
    }
}
SysLogger log = LoggerFactory.getLogicflowSysLogger();
com.primeton.ext.common.logging.EngineLogger engineLog = null;
sessionContext = DataContextManager.current().getMUODataContext();
engineLog = LoggerFactory.getBizlogicCurrentEngineLogger();
try{
this.__context.setName(this.getQName());
this.__context.setProcessName(this.getProcessName());
this.beforeHandlers(null,__context);
IUserObject __userObject = null;if(sessionContext != null)__userObject = ((com.eos.data.datacontext.IMUODataContext)sessionContext).getUserObject();String __remoteIP = null;if(__userObject != null) __remoteIP = __userObject.getUserRemoteIP();com.primeton.ext.common.statistic.BizflowStatisticMessage __item=new com.primeton.ext.common.statistic.BizflowStatisticMessage(this.getQName(),DataContextManager.current().getContextStack().getUniqueID(),__remoteIP);
if(__userObject != null) __item.setUserId(__userObject.getUserId());
com.eos.common.statistic.StatisticManager.push(__item);
int __paramDefineSize = 2;
if(__params == null || __params.length < __paramDefineSize){
throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100063, new Object[]{this.getQName()});
}
java.lang.String userId;
try{
 userId = DataUtil.toString(__params[0]);
}catch(java.lang.ClassCastException ce){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:userId ,Input param object type: "+__params[0].getClass().getName() +" not match with define:java.lang.String",new Object[]{this.getQName(),"userId",__params[0].getClass().getName(),"java.lang.String"});
}
java.lang.String password;
try{
 password = DataUtil.toString(__params[1]);
}catch(java.lang.ClassCastException ce){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:password ,Input param object type: "+__params[1].getClass().getName() +" not match with define:java.lang.String",new Object[]{this.getQName(),"password",__params[1].getClass().getName(),"java.lang.String"});
}
log.entry();
engineLog.log("start", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context));
Object[] results =  _invoke(userId,password);//2//
this.afterHandlers(null,__context);
log.exit();
engineLog.log("end", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context));
if(results == null){
  results = new Object[0];
}
return results;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
  log.exception(__ex.getMessage());
  engineLog.log("exception", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context), __ex);
  throw __ex;
}catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers(null,__context,__ex);
  log.exception(__ex.getMessage());
  engineLog.log("exception", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context), __ex);
  throw __ex;
}finally{
com.eos.common.statistic.StatisticManager.pop();
	TransactionManagerProxy txProxy = this._eosInternal.getTxProxy();
	if(txProxy != null){
		if(!txProxy.isNestTx() && txProxy.getBeginCount() > 0){
			txProxy.forceRollback();
		}
	}
this.finallyHandlers(null,__context);
sessionContext = null;
}
}
public void __restoreField()  throws Throwable{
      this.userObject=(com.eos.data.datacontext.UserObject)__context.get("userObject");
      this.retCode=__context.getInt("retCode");
}
public void __invokeBiz()  throws Throwable{
int __eosCurrentActivityID= __context.getCursorId();
if(__eosCurrentActivityID==-10000){
__eosCurrentActivityID=10000;
}
  while(__eosCurrentActivityID!=0){       if(isSuspend()){           break;       }//1,2//
    switch(__eosCurrentActivityID){
    case 10000:
__eosCurrentActivityID = __choicestart0(__eosCurrentActivityID);//start0//
      break;
    case 40000:
__eosCurrentActivityID = __choiceend0(__eosCurrentActivityID);//end0//
      break;
    case 190000:
__eosCurrentActivityID = __choicesubprocess0(__eosCurrentActivityID);//subprocess0//
      break;
    case 400000:
__eosCurrentActivityID = __choiceinvokeSpring0(__eosCurrentActivityID);//invokeSpring0//
      break;
    case 400001:
__eosCurrentActivityID = __choiceinvokeSpring1(__eosCurrentActivityID);//invokeSpring1//
      break;
    case -4:
      __eosCurrentActivityID= __breakLoop();
      break;
    case -5:
      __eosCurrentActivityID= __continueLoop();
      break;
    default: __eosCurrentActivityID=-1;
    callBack(__results);
  return;
  }
  }
}
private int __choicestart0(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=start0(__context,userId,password);
      userId=__context.getString("userId");
      password=__context.getString("password");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceend0(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=end0(__context,userId,password);
      userId=__context.getString("userId");
      password=__context.getString("password");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choicesubprocess0(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=subprocess0(__context,userId,password);
      userId=__context.getString("userId");
      password=__context.getString("password");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokeSpring0(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=invokeSpring0(__context,userId,password);
      userId=__context.getString("userId");
      password=__context.getString("password");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokeSpring1(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=invokeSpring1(__context,userId,password);
      userId=__context.getString("userId");
      password=__context.getString("password");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
}
