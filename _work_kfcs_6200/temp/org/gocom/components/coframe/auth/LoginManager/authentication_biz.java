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
public class authentication_biz extends com.primeton.engine.core.impl.AbstractJavaBL{
com.eos.data.datacontext.IDataContext sessionContext = null;
Object[] __results = new Object[1];;
int isend;
int returnValue;
public authentication_biz(){
}
public authentication_biz(java.util.Map head,java.util.Map attachment){
super(head,attachment);
}
public String getQName(){
  return "org.gocom.components.coframe.auth.LoginManager.authentication.biz";
}
public String getShortName(){
  return "authentication";
}
public List<Activity> getActivities(){
  if(this._eosInternal.activities==null){
    this._eosInternal.activities = new ArrayList<Activity>();
    this._eosInternal.activities.add(new Activity("start0","开始","start"));
    this._eosInternal.activities.add(new Activity("end0","结束","end"));
    this._eosInternal.activities.add(new Activity("invokeSpring0","根据用户Id获得用户信息","invoke"));
    this._eosInternal.activities.add(new Activity("assign0","认证失败","assign"));
    this._eosInternal.activities.add(new Activity("assign1","认证成功","assign"));
    this._eosInternal.activities.add(new Activity("assign2","用户不存在","assign"));
    this._eosInternal.activities.add(new Activity("switch0","空操作","switch"));
    this._eosInternal.activities.add(new Activity("invokeSpring1","ldap认证","invoke"));
    this._eosInternal.activities.add(new Activity("invokeSpring2","密码加密","invoke"));
    this._eosInternal.activities.add(new Activity("assign3","用户状态非正常","assign"));
    this._eosInternal.activities.add(new Activity("assign4","数据库异常","assign"));
    this._eosInternal.activities.add(new Activity("end1","结束1","end"));
    this._eosInternal.activities.add(new Activity("subprocess0","是否过期","subprocess"));
    this._eosInternal.activities.add(new Activity("assign5","赋值","assign"));
    this._eosInternal.activities.add(new Activity("invokeSpring3","记录用户最后登录时间","invoke"));
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
      __outputVariables[0].setName("returnValue");
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
  __eosCurrentActivityID=400000;
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
  __results[0]=com.primeton.ext.data.sdo.DataUtil.toInt(this.returnValue);
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
private int invokeSpring0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring0");
__context.setCurrentActivityName("根据用户Id获得用户信息");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring0",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = com.primeton.ext.data.sdo.DataUtil.toString(userId);
Class[]__paramTypes = new Class[1];
__paramTypes[0] = java.lang.String.class;
	  Object __return = com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("CapUserService","getCapUserByUserId",__paramTypes, __params);

__context.setDataObject("capUser",__return);
}finally{
}
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capUser"), (Object)null ,"ISNULL")){
    __eosCurrentActivityID=20002;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=190000;
  this.afterHandlers("invokeSpring0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(java.lang.Exception __ex){
  this.exceptionHandlers("invokeSpring0",__context,__ex);
  __context.set("__exception",__ex);
  __eosCurrentActivityID=20004;
  Logger logger = TraceLoggerFactory.getLogger(this.getClass());
  logger.error("[Name="+this.getQName()+"][activity name=根据用户Id获得用户信息][activity id=invokeSpring0] throw an exception:java.lang.Exception,exception :",__ex);
  return __eosCurrentActivityID;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=根据用户Id获得用户信息][activity id=invokeSpring0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring0",__context);
}
}
private int assign0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign0");
__context.setCurrentActivityName("认证失败");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign0",__context);
  __context.set("returnValue",Integer.valueOf("0").intValue());
  this.returnValue = __context.getInt("returnValue");
  __eosCurrentActivityID=40000;
  this.afterHandlers("assign0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=认证失败][activity id=assign0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign0",__context);
}
}
private int assign1(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign1");
__context.setCurrentActivityName("认证成功");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign1",__context);
  __context.set("returnValue",Integer.valueOf("1").intValue());
  this.returnValue = __context.getInt("returnValue");
  __eosCurrentActivityID=400003;
  this.afterHandlers("assign1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=认证成功][activity id=assign1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign1",__context);
}
}
private int assign2(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign2");
__context.setCurrentActivityName("用户不存在");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign2",__context);
  __context.set("returnValue",Integer.valueOf("-1").intValue());
  this.returnValue = __context.getInt("returnValue");
  __eosCurrentActivityID=40001;
  this.afterHandlers("assign2",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign2",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=用户不存在][activity id=assign2][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign2",__context);
}
}
private int switch0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("switch0");
__context.setCurrentActivityName("空操作");
__context.setCurrentActivityType("switch");
__context.setCurrentActivityValue(null);
this.beforeHandlers("switch0",__context);
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capUser/authmode"),"ldap","EQ")){
    __eosCurrentActivityID=400001;
    return __eosCurrentActivityID;
  }
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capUser/status"),"1","NE")){
    __eosCurrentActivityID=20003;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=400002;
  this.afterHandlers("switch0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("switch0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=空操作][activity id=switch0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("switch0",__context);
}
}
private int invokeSpring1(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring1");
__context.setCurrentActivityName("ldap认证");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring1",__context);
try{
  Object[] __params =  new Object[2];
__params[0] = __context.getString("user/userId");
__params[1] = __context.getString("user/password");
Class[]__paramTypes = new Class[2];
__paramTypes[0] = java.lang.String.class;
__paramTypes[1] = java.lang.String.class;
	  Object __return = com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("LDAPAuthenticatorBean","authenricate",__paramTypes, __params);

__context.setInt("returnValue",__return);
    this.returnValue=__context.getInt("returnValue");
}finally{
}
  __eosCurrentActivityID=40000;
  this.afterHandlers("invokeSpring1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=ldap认证][activity id=invokeSpring1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring1",__context);
}
}
private int invokeSpring2(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring2");
__context.setCurrentActivityName("密码加密");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring2",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = com.primeton.ext.data.sdo.DataUtil.toString(password);
Class[]__paramTypes = new Class[1];
__paramTypes[0] = java.lang.String.class;
	  Object __return = com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("CapUserBean","encodePassword",__paramTypes, __params);

__context.setString("password",__return);
    password=__context.getString("password");
}finally{
}
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(__context.get("capUser/password"),password,"EQ")){
    __eosCurrentActivityID=20001;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=20000;
  this.afterHandlers("invokeSpring2",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring2",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=密码加密][activity id=invokeSpring2][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring2",__context);
}
}
private int assign3(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign3");
__context.setCurrentActivityName("用户状态非正常");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign3",__context);
  __context.set("returnValue",Integer.valueOf("-2").intValue());
  this.returnValue = __context.getInt("returnValue");
  __eosCurrentActivityID=40000;
  this.afterHandlers("assign3",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign3",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=用户状态非正常][activity id=assign3][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign3",__context);
}
}
private int assign4(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign4");
__context.setCurrentActivityName("数据库异常");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign4",__context);
  __context.set("returnValue",Integer.valueOf("-3").intValue());
  this.returnValue = __context.getInt("returnValue");
  __eosCurrentActivityID=40001;
  this.afterHandlers("assign4",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign4",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=数据库异常][activity id=assign4][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign4",__context);
}
}
private int end1(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("end1");
__context.setCurrentActivityName("结束1");
__context.setCurrentActivityType("end");
__context.setCurrentActivityValue(null);
this.beforeHandlers("end1",__context);
  __results = new Object[1];
  __results[0]=com.primeton.ext.data.sdo.DataUtil.toInt(this.returnValue);
  __eosCurrentActivityID=-1;
  this.afterHandlers("end1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("end1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=结束1][activity id=end1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("end1",__context);
}
}
private int subprocess0(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("subprocess0");
__context.setCurrentActivityName("是否过期");
__context.setCurrentActivityType("subprocess");
__context.setCurrentActivityValue("this.isend");
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
    String __optName = "isend";
try{
    ILogicComponent __component = (ILogicComponent)this.__component;
  Object[] __params =  new Object[1];
__params[0] = (commonj.sdo.DataObject)__context.getDataObject("capUser");
	  Object[]___return = __component.invoke(__optName, __params,"join");
    __context.setInt("isend",___return[0]);
    this.isend=__context.getInt("isend");
}catch(com.primeton.engine.core.exception.EOSEngineRuntimeException __ex){
  if(__ex.getCause()!=null){
    throw __ex.getCause();
  }else{
    throw __ex;
  }}finally{
com.primeton.ext.access.ejb.jta.JtaContextManager.current().setWithinJta(__oldStatus);
}
  if(  com.primeton.ext.engine.core.SimpleConditionHelper.compare(this.isend,"1","EQ")){
    __eosCurrentActivityID=30000;
    return __eosCurrentActivityID;
  }
  __eosCurrentActivityID=20005;
  this.afterHandlers("subprocess0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("subprocess0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=是否过期][activity id=subprocess0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("subprocess0",__context);
}
}
private int assign5(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("assign5");
__context.setCurrentActivityName("赋值");
__context.setCurrentActivityType("assign");
__context.setCurrentActivityValue(null);
this.beforeHandlers("assign5",__context);
  __context.set("returnValue",com.primeton.ext.data.sdo.DataUtil.toInt(this.isend));
  this.returnValue = __context.getInt("returnValue");
  __eosCurrentActivityID=40001;
  this.afterHandlers("assign5",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("assign5",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=赋值][activity id=assign5][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("assign5",__context);
}
}
private int invokeSpring3(RuntimeContext __context,java.lang.String userId,java.lang.String password) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring3");
__context.setCurrentActivityName("记录用户最后登录时间");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring3",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = (org.gocom.components.coframe.rights.dataset.CapUser)__context.getDataObject("capUser");
Class[]__paramTypes = new Class[1];
__paramTypes[0] = org.gocom.components.coframe.rights.dataset.CapUser.class;
com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("CapUserService","updateLastLogin",__paramTypes, __params);

}finally{
}
  __eosCurrentActivityID=40000;
  this.afterHandlers("invokeSpring3",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring3",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=记录用户最后登录时间][activity id=invokeSpring3][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring3",__context);
}
}
public Object[] _invoke(java.lang.String userId,java.lang.String password) throws Throwable{
       if(isSuspend()){
           return null;
       }
  __context.set("userId",userId);
  __context.set("password",password);
  __context.set("isend",isend);
  __context.set("returnValue",returnValue);
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
      this.isend=__context.getInt("isend");
      this.returnValue=__context.getInt("returnValue");
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
    case 400000:
__eosCurrentActivityID = __choiceinvokeSpring0(__eosCurrentActivityID);//invokeSpring0//
      break;
    case 20000:
__eosCurrentActivityID = __choiceassign0(__eosCurrentActivityID);//assign0//
      break;
    case 20001:
__eosCurrentActivityID = __choiceassign1(__eosCurrentActivityID);//assign1//
      break;
    case 20002:
__eosCurrentActivityID = __choiceassign2(__eosCurrentActivityID);//assign2//
      break;
    case 30000:
__eosCurrentActivityID = __choiceswitch0(__eosCurrentActivityID);//switch0//
      break;
    case 400001:
__eosCurrentActivityID = __choiceinvokeSpring1(__eosCurrentActivityID);//invokeSpring1//
      break;
    case 400002:
__eosCurrentActivityID = __choiceinvokeSpring2(__eosCurrentActivityID);//invokeSpring2//
      break;
    case 20003:
__eosCurrentActivityID = __choiceassign3(__eosCurrentActivityID);//assign3//
      break;
    case 20004:
__eosCurrentActivityID = __choiceassign4(__eosCurrentActivityID);//assign4//
      break;
    case 40001:
__eosCurrentActivityID = __choiceend1(__eosCurrentActivityID);//end1//
      break;
    case 190000:
__eosCurrentActivityID = __choicesubprocess0(__eosCurrentActivityID);//subprocess0//
      break;
    case 20005:
__eosCurrentActivityID = __choiceassign5(__eosCurrentActivityID);//assign5//
      break;
    case 400003:
__eosCurrentActivityID = __choiceinvokeSpring3(__eosCurrentActivityID);//invokeSpring3//
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
private int __choiceassign0(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=assign0(__context,userId,password);
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
private int __choiceassign1(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=assign1(__context,userId,password);
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
private int __choiceassign2(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=assign2(__context,userId,password);
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
private int __choiceswitch0(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=switch0(__context,userId,password);
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
private int __choiceinvokeSpring2(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=invokeSpring2(__context,userId,password);
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
private int __choiceassign3(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=assign3(__context,userId,password);
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
private int __choiceassign4(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=assign4(__context,userId,password);
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
private int __choiceend1(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=end1(__context,userId,password);
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
private int __choiceassign5(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=assign5(__context,userId,password);
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
private int __choiceinvokeSpring3(int __eosCurrentActivityID) throws Throwable{
  java.lang.String userId;
  java.lang.String password;
      userId=__context.getString("userId");
      password=__context.getString("password");
    try{
      __eosCurrentActivityID=invokeSpring3(__context,userId,password);
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
