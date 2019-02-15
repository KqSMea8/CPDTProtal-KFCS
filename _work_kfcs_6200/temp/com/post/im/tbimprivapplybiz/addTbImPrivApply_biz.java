package com.post.im.tbimprivapplybiz;
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
public class addTbImPrivApply_biz extends com.primeton.engine.core.impl.AbstractJavaBL{
com.eos.data.datacontext.IDataContext sessionContext = null;
Object[] __results = new Object[0];;
commonj.sdo.DataObject tbimapplyappr=com.primeton.ext.data.sdo.helper.ExtendedDataFactory.eINSTANCE.create("com.post.im.resourceM.appr.appr.TbImApplyAppr");
{__context.addTypeMapping("xpath:/tbimapplyappr","java:null");}
{__context.addTypeMapping("xpath:/tbimapplyappr","sdo:com.post.im.resourceM.appr.appr.TbImApplyAppr");}
public addTbImPrivApply_biz(){
     __context.set("tbimapplyappr",tbimapplyappr);
}
public addTbImPrivApply_biz(java.util.Map head,java.util.Map attachment){
super(head,attachment);
     __context.set("tbimapplyappr",tbimapplyappr);
}
public String getQName(){
  return "com.post.im.tbimprivapplybiz.addTbImPrivApply.biz";
}
public String getShortName(){
  return "addTbImPrivApply.bizx";
}
public List<Activity> getActivities(){
  if(this._eosInternal.activities==null){
    this._eosInternal.activities = new ArrayList<Activity>();
    this._eosInternal.activities.add(new Activity("start0","开始","start"));
    this._eosInternal.activities.add(new Activity("end0","结束","end"));
    this._eosInternal.activities.add(new Activity("invokePojo0","insertEntity","invoke"));
    this._eosInternal.activities.add(new Activity("invokePojo1","getPrimaryKey","invoke"));
    this._eosInternal.activities.add(new Activity("subprocess0","更新明细表","subprocess"));
    this._eosInternal.activities.add(new Activity("invokeSpring0","dataParseToDetail","invoke"));
    this._eosInternal.activities.add(new Activity("invokeSpring1","dataParseToBacklog","invoke"));
    this._eosInternal.activities.add(new Activity("subprocess1","插入待办任务信息表","subprocess"));
    this._eosInternal.activities.add(new Activity("invokeSpring2","dataParseToApply","invoke"));
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
      Variable[]__inputVariables = new Variable[1];
      __inputVariables[0] = new Variable();
      __inputVariables[0].setArray(false);
      __inputVariables[0].setCategory(3);
      __inputVariables[0].setDataObjectType("com.post.im.resourceM.priv.priv.TbImPrivApply");
      __inputVariables[0].setName("tbimprivapply");
      __inputVariables[0].setTypeClass(commonj.sdo.DataObject.class);
      __inputVariables[0].setTypeName("commonj.sdo.DataObject");

      Variable[]__outputVariables = new Variable[0];
      this._eosInternal.getAttrs().put(Operation.BIZ_INPUT_VARIBLE, __inputVariables);
      this._eosInternal.getAttrs().put(Operation.BIZ_OUTPUT_VARIBLE, __outputVariables);
      variables = (Variable[])this._eosInternal.getAttrs().get(category);
   }
   return variables;
}
private int start0(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("start0");
__context.setCurrentActivityName("开始");
__context.setCurrentActivityType("start");
__context.setCurrentActivityValue(null);
this.beforeHandlers("start0",__context);
  __eosCurrentActivityID=400002;
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
private int end0(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("end0");
__context.setCurrentActivityName("结束");
__context.setCurrentActivityType("end");
__context.setCurrentActivityValue(null);
this.beforeHandlers("end0",__context);
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
private int invokePojo0(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokePojo0");
__context.setCurrentActivityName("insertEntity");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue("com.eos.foundation.database.DatabaseUtil.insertEntity");
this.beforeHandlers("invokePojo0",__context);
long __startTime=System.currentTimeMillis();
try{
  __resumeException();
   if(!__context.getAttributes().containsKey("invokePojo0__suspend")){
com.eos.foundation.database.DatabaseUtil.insertEntity("default",tbimprivapply);//pojo:invokePojo0//
if(isSuspend()){
return 70000;
}
}else{
      __context.getAttributes().remove("invokePojo0__suspend");
}
}finally{
 if(EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout() >= 0 ){
  long __executeTime = System.currentTimeMillis() - __startTime;
  if (__executeTime >= EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout()) {
    com.primeton.ext.common.logging.LoggerFactory.getPojoSysLogger().perform("com.eos.foundation.database.DatabaseUtil.insertEntity", String.valueOf(__executeTime));
  }
 }
}
  __eosCurrentActivityID=400000;
  this.afterHandlers("invokePojo0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokePojo0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=insertEntity][activity id=invokePojo0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokePojo0",__context);
}
}
private int invokePojo1(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokePojo1");
__context.setCurrentActivityName("getPrimaryKey");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue("com.eos.foundation.database.DatabaseExt.getPrimaryKey");
this.beforeHandlers("invokePojo1",__context);
long __startTime=System.currentTimeMillis();
try{
  __resumeException();
   if(!__context.getAttributes().containsKey("invokePojo1__suspend")){
com.eos.foundation.database.DatabaseExt.getPrimaryKey(tbimprivapply);//pojo:invokePojo1//
if(isSuspend()){
return 70001;
}
}else{
      __context.getAttributes().remove("invokePojo1__suspend");
}
}finally{
 if(EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout() >= 0 ){
  long __executeTime = System.currentTimeMillis() - __startTime;
  if (__executeTime >= EngineConfigHandler.getEngineConfigModel().getLogPojoWhenTimeout()) {
    com.primeton.ext.common.logging.LoggerFactory.getPojoSysLogger().perform("com.eos.foundation.database.DatabaseExt.getPrimaryKey", String.valueOf(__executeTime));
  }
 }
}
  __eosCurrentActivityID=70000;
  this.afterHandlers("invokePojo1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokePojo1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=getPrimaryKey][activity id=invokePojo1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokePojo1",__context);
}
}
private int subprocess0(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("subprocess0");
__context.setCurrentActivityName("更新明细表");
__context.setCurrentActivityType("subprocess");
__context.setCurrentActivityValue("com.post.im.tbimapplyapprbiz.addTbImApplyAppr");
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
    String __componentName = "com.post.im.tbimapplyapprbiz";
    String __optName = "addTbImApplyAppr";
try{
    ILogicComponent __component = new ServiceContext().locateBizLogic(__appName+__componentName);
  Object[] __params =  new Object[1];
__params[0] = this.tbimapplyappr;
__component.invoke(__optName, __params,"join");
}catch(com.primeton.engine.core.exception.EOSEngineRuntimeException __ex){
  if(__ex.getCause()!=null){
    throw __ex.getCause();
  }else{
    throw __ex;
  }}finally{
com.primeton.ext.access.ejb.jta.JtaContextManager.current().setWithinJta(__oldStatus);
}
  __eosCurrentActivityID=400001;
  this.afterHandlers("subprocess0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("subprocess0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=更新明细表][activity id=subprocess0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("subprocess0",__context);
}
}
private int invokeSpring0(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring0");
__context.setCurrentActivityName("dataParseToDetail");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring0",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = tbimprivapply;
Class[]__paramTypes = new Class[1];
__paramTypes[0] = commonj.sdo.DataObject.class;
	  Object __return = com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("PrivDetailBean","dataParseAdd",__paramTypes, __params);

__context.setDataObject("tbimapplyappr",__return);
    this.tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
{DataObject _____tmp = (DataObject)this.tbimapplyappr;
if(_____tmp!=null){
com.primeton.engine.core.impl.helper.DataBuilder.checkedDataObjectIsAssignableFrom(_____tmp,"com.post.im.resourceM.appr.appr.TbImApplyAppr");
}
}
}finally{
}
  __eosCurrentActivityID=190000;
  this.afterHandlers("invokeSpring0",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring0",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=dataParseToDetail][activity id=invokeSpring0][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring0",__context);
}
}
private int invokeSpring1(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring1");
__context.setCurrentActivityName("dataParseToBacklog");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring1",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = tbimprivapply;
Class[]__paramTypes = new Class[1];
__paramTypes[0] = commonj.sdo.DataObject.class;
	  Object __return = com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("PrivBacklogBean","dataParseToBacklogAdd",__paramTypes, __params);

__context.setDataObject("tbimbackloginfo",__return);
}finally{
}
  __eosCurrentActivityID=190001;
  this.afterHandlers("invokeSpring1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=dataParseToBacklog][activity id=invokeSpring1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring1",__context);
}
}
private int subprocess1(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
try{
__context.setCurrentActivityID("subprocess1");
__context.setCurrentActivityName("插入待办任务信息表");
__context.setCurrentActivityType("subprocess");
__context.setCurrentActivityValue("com.post.im.tbimbackloginfobiz.addTbImBacklogInfo");
this.beforeHandlers("subprocess1",__context);
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
    String __componentName = "com.post.im.tbimbackloginfobiz";
    String __optName = "addTbImBacklogInfo";
try{
    ILogicComponent __component = new ServiceContext().locateBizLogic(__appName+__componentName);
  Object[] __params =  new Object[1];
__params[0] = (commonj.sdo.DataObject)__context.getDataObject("tbimbackloginfo");
__component.invoke(__optName, __params,"join");
}catch(com.primeton.engine.core.exception.EOSEngineRuntimeException __ex){
  if(__ex.getCause()!=null){
    throw __ex.getCause();
  }else{
    throw __ex;
  }}finally{
com.primeton.ext.access.ejb.jta.JtaContextManager.current().setWithinJta(__oldStatus);
}
  __eosCurrentActivityID=40000;
  this.afterHandlers("subprocess1",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("subprocess1",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=插入待办任务信息表][activity id=subprocess1][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("subprocess1",__context);
}
}
private int invokeSpring2(RuntimeContext __context,commonj.sdo.DataObject tbimprivapply) throws Throwable{
com.eos.data.datacontext.IDataContext context = __context;
  int __eosCurrentActivityID = -1;
boolean __oldStatusService = com.primeton.ext.access.ejb.jta.JtaContextManager.current().isWithinJta();
try{
__context.setCurrentActivityID("invokeSpring2");
__context.setCurrentActivityName("dataParseToApply");
__context.setCurrentActivityType("invoke");
__context.setCurrentActivityValue(null);
this.beforeHandlers("invokeSpring2",__context);
try{
  Object[] __params =  new Object[1];
__params[0] = tbimprivapply;
Class[]__paramTypes = new Class[1];
__paramTypes[0] = commonj.sdo.DataObject.class;
	  Object __return = com.eos.engine.core.EngineSpringInvokerFactory.getSpringInvoker().invoke("PrivApplyBean","dataParseAdd",__paramTypes, __params);

__context.setDataObject("tbimprivapply",__return);
    tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
{DataObject _____tmp = (DataObject)tbimprivapply;
if(_____tmp!=null){
com.primeton.engine.core.impl.helper.DataBuilder.checkedDataObjectIsAssignableFrom(_____tmp,"com.post.im.resourceM.priv.priv.TbImPrivApply");
}
}
}finally{
}
  __eosCurrentActivityID=70001;
  this.afterHandlers("invokeSpring2",__context);
  return __eosCurrentActivityID;
}catch(com.eos.engine.core.exception.HandlerTerminateException __ex){
  __context.set("__exception",__ex);
   throw __ex;
}
catch(Throwable  __ex){
  __context.set("__exception",__ex);
  this.exceptionHandlers("invokeSpring2",__context,__ex);
  String __errorMessage = "[FlowName="+this.getQName()+"][activity name=dataParseToApply][activity id=invokeSpring2][exception=" +__ex.toString()+"]"; 
  if("true".equals(System.getProperty("EOS_DEBUG"))){
    System.err.println(__errorMessage);
  }
  throw __ex;
}
finally{
  this.finallyHandlers("invokeSpring2",__context);
}
}
public Object[] _invoke(commonj.sdo.DataObject tbimprivapply) throws Throwable{
       if(isSuspend()){
           return null;
       }
  __context.set("tbimprivapply",tbimprivapply);
  __context.set("tbimapplyappr",tbimapplyappr);
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
int __paramDefineSize = 1;
if(__params == null || __params.length < __paramDefineSize){
throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100063, new Object[]{this.getQName()});
}
commonj.sdo.DataObject tbimprivapply;
try{
 tbimprivapply = (commonj.sdo.DataObject)__params[0];
if(__params[0]==null){__context.addTypeMapping("xpath:/tbimprivapply","sdo:com.post.im.resourceM.priv.priv.TbImPrivApply");}
if(__params[0] != null){
  if(!(__params[0] instanceof commonj.sdo.DataObject)){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:tbimprivapply ,Input param object type: "+__params[0].getClass().getName() +" not match with define:commonj.sdo.DataObject:com.post.im.resourceM.priv.priv.TbImPrivApply",new Object[]{this.getQName(),"tbimprivapply",__params[0].getClass().getName(),"commonj.sdo.DataObject:com.post.im.resourceM.priv.priv.TbImPrivApply"});
  }
try{
  if(!"com.post.im.resourceM.priv.priv.TbImPrivApply".equals(((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName())){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100067,"Param Name:tbimprivapply ,Input param object type: "+((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName()+" not match with define:commonj.sdo.DataObject:com.post.im.resourceM.priv.priv.TbImPrivApply",new Object[]{this.getQName(),"tbimprivapply",((commonj.sdo.DataObject)__params[0]).getType().getURI() + "." +((commonj.sdo.DataObject)__params[0]).getType().getName(),"commonj.sdo.DataObject:com.post.im.resourceM.priv.priv.TbImPrivApply"});
  }
 }
 catch(EOSEngineRuntimeException ___e){tracelog.warn(___e.getMessage());}}
}catch(java.lang.ClassCastException ce){
    throw new EOSEngineRuntimeException(ExceptionConstant.EOSEngineRuntimeException_16100066,"Param Name:tbimprivapply ,Input param object type: "+__params[0].getClass().getName() +" not match with define:commonj.sdo.DataObject",new Object[]{this.getQName(),"tbimprivapply",__params[0].getClass().getName(),"commonj.sdo.DataObject"});
}
log.entry();
engineLog.log("start", "[@qName][" + this.getQName() + "]" + com.primeton.ext.common.logging.LoggerContext.getEngineLoggerContextString(this.__context));
Object[] results =  _invoke(tbimprivapply);//2//
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
      this.tbimapplyappr=(commonj.sdo.DataObject)__context.getDataObject("tbimapplyappr");
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
    case 70000:
__eosCurrentActivityID = __choiceinvokePojo0(__eosCurrentActivityID);//invokePojo0//
      break;
    case 70001:
__eosCurrentActivityID = __choiceinvokePojo1(__eosCurrentActivityID);//invokePojo1//
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
    case 190001:
__eosCurrentActivityID = __choicesubprocess1(__eosCurrentActivityID);//subprocess1//
      break;
    case 400002:
__eosCurrentActivityID = __choiceinvokeSpring2(__eosCurrentActivityID);//invokeSpring2//
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
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=start0(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceend0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=end0(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokePojo0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=invokePojo0(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokePojo1(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=invokePojo1(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choicesubprocess0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=subprocess0(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokeSpring0(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=invokeSpring0(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokeSpring1(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=invokeSpring1(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choicesubprocess1(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=subprocess1(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
private int __choiceinvokeSpring2(int __eosCurrentActivityID) throws Throwable{
  commonj.sdo.DataObject tbimprivapply;
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
    try{
      __eosCurrentActivityID=invokeSpring2(__context,tbimprivapply);
      tbimprivapply=(commonj.sdo.DataObject)__context.getDataObject("tbimprivapply");
__restoreField();
      __context.setCursorId(__eosCurrentActivityID);
    }
    catch(Throwable e){
      __eosCurrentActivityID= __throwException(e);
    }
return __eosCurrentActivityID;
}
}
