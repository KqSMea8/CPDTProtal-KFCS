<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): zhoujl
  - Date: 2019-01-15 17:06:41
  - Description:
-->
<head>
<title>测试工具排期</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="<%=request.getContextPath()%>/common/nui/nui.js"
	type="text/javascript"></script>
<script
	src="<%=request.getContextPath()%>/tm/testToolM/echarts.min.js"
	type="text/javascript"></script>
<style type="text/css">
#main {
	background-color: #fff;
	border-radius: 20px;
	width: 60%;
	height: 70%;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
}
</style>
</head>
<body>
	<div align="center">
		<div>
			<h2>
				<button onclick="lastMonth();">上个月</button>
				<span id="date"></span>
				<button onclick="nextMonth();">下个月</button>
			</h2>
		</div>
	</div>
	<div id="main"></div>
	<script type="text/javascript">
		nui.parse();
		
		
		var myChart = echarts.init(document.getElementById('main'));
			//热力数据
			var heatmapData = [];
			//日历日期数据
			var lunarData = [];
			
			//对象数组
			var objArrData =[]; 
			//改版
			var date = new Date();
			var daysOfMonth = [];
			var str = date.getFullYear() + '-' + (date.getMonth() + 1); //当前日期
			document.getElementById('date').innerHTML = str;
			var fullYear = date.getFullYear();

			var month = date.getMonth() + 1;
			var objArrTest2 =  {};
			getVirtulData(fullYear, month);
			
			
			
			var mydate = fullYear.toString() + '-' + month.toString();

			//初始化数据
			var option = {
				tooltip: {
					formatter: function(params) {
						return objArrData[objDateToday(params.value[0])-1];
					}
				},
				visualMap: {
					show: false,
					min: 0,
					max: 1000,
					calculable: true,
					left: 'center',
					bottom: 20,
					inRange: {
						color: ['#e0ffff', '#006edd'],
						opacity: 2
					},
					controller: {
						inRange: {
							opacity: 0.5
						}
					}
				},
				calendar: [{
					range: mydate,
					orient: 'vertical',
					left: 'center',
					textStyle: 'black',
					top: 'bottom',
					cellSize: 'auto',
					dayLabel: {
						firstDay: 1,
						nameMap: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
						align: 'center',
						position: 'start',
					},
					monthLabel: {
						nameMap: 'cn',
						verticalAlign: 'middle',
						position: 'start'
					},
					yearLabel: {
						position: 'right'
					},
					silent: false
				}],
				series: [{
					id: 'label',
					type: 'scatter',
					name: 'abc',
					coordinateSystem: 'calendar',
					symbolSize: 1,
					label: {
						normal: {
							show: true,
							formatter: function(params) {
								return echarts.format.formatTime('dd', params.value[0]);
							},
							textStyle: {
								color: '#000',
								fontSize: 14
							}
						}
					},
					data: lunarData
				}, {
					name: 'uuu',
					type: 'heatmap',
					coordinateSystem: 'calendar',
					data: heatmapData
				}]
			};
			myChart.setOption(option);

			//设置热力和数据
			function getVirtulData(year, month) {
				year = year || 2017;
				month = month || 1;
				lunarData = [];
				heatmapData = [];
				objArrData = [];
				var date = +echarts.number.parseDate(year + '-' + month + '-01');
				var end = +echarts.number.parseDate(year + '-' + (month + 1) + '-01');
				var dayTime = 3600 * 24 * 1000;
				for (var time = date; time < end; time += dayTime) {
					lunarData.push([
						echarts.format.formatTime('yyyy-MM-dd', time),
						Math.floor(Math.random() * 10000)
					]);
					heatmapData.push([
						echarts.format.formatTime('yyyy-MM-dd', time),
						50
					]);
					objArrData.push("今日暂无排期信息");
				}
				getObjArrData(year,month);
			}
			myChart.on('click', {
				seriesName: 'uuu'
			}, function(params) {
			
				
				var obj = {};
				if(objArrData[objDateToday(params.value[0])-1]!="今日暂无排期信息"){
					obj = stringToObj(objArrData[objDateToday(params.value[0])-1]);
					obj.sche = params.value[0];
					obj.type = 'edit';
				}else{
					obj.sche = params.value[0];
					obj.type = 'add';
				}
				openForm(obj);
			});
			
			//对象转显示字符串
			function objToString(obj){
				return '测试工具名称:'+obj.use_tool_nm+'<br/>测试项名称:'+obj.test_items_nm+'<br/>使用人员:'+obj.user_nm+'<br/>rec_id:'+obj.rec_id;
			}
			
			//字符串转对象
			function stringToObj(str){
				var keyAndValue = str.split('<br/>');
				var obj = {};
				var result = [];
				for(var i = 0;i<keyAndValue.length;i++){
					var value = keyAndValue[i].split(':');
					result.push(value[1]);
				}
				obj = {
					useToolNm:result[0],
					testItemsNm:result[1],
					userNm:result[2],
					recId:result[3]
				};
				return obj;
			}
			
			//遍历对象数组，摘取日期并添加至数据数组
			function travObjArr(objArr){
				for(var i = 0;i<objArr.length;i++){
					heatmapData[objDateToday(objArr[i].sche) - 1].splice(1,1,350);
					objArrData.splice(objDateToday(objArr[i].sche) - 1,1,objToString(objArr[i]));
				}
			}
			//将日期拆分，返回dayOfMonth
			function objDateToday(date){
				var values = date.toString().split('-');
				return parseInt(values[2]);
			}
			
			//获取数据库排期信息(查询当月的排期信息)
			function getObjArrData(year,month){
				if(month<10){
					month = '0'+month;
				}
				var begin = year + '-' +month +'-' +'01';
				var lastDayOfMonth = new Date(year,month,0).getDate();
				var end = year + '-' +month + '-'+ lastDayOfMonth;
				
				var json = nui.encode({
					begin:begin,
					end:end
				});
				$.ajax({
					url:'com.post.tm.toolM.testToolM.testToolScheQuery.biz.ext',
					type:'POST',
					data:json,
					cache:false,
					async:false,
					contentType:'text/json',
					success:function(data){
						objArrTest2 =  data.testToolScheInfo;
						travObjArr(objArrTest2);
					}
				});
				
			}
			
			function openForm(obj){
	
				nui.open({
					url : "<%=request.getContextPath()%>/tm/testToolM/testToolScheForm.jsp",
						title : date + "排期信息",
						width : 600,
						height : 360,
						onload : function() {
							var iframe = this.getIFrameEl();
							var data = {
								info : obj
							};
							//直接从页面获取，不用去后台获取
							iframe.contentWindow.setFormData(data);
						},
						ondestroy : function(action) {
							var currentDate = document.getElementById('date').innerHTML;
							var value = currentDate.split('-');
							parseInt(value[0]);
							parseInt(value[1]);
							var str = fullYear + '-' + month;
							getVirtulData(fullYear, month);
							optionLast = {
								calendar : [ {
									range : str,
								} ],
								series : [ {
									data : lunarData
								}, {
									data : heatmapData
								} ]
							};
							myChart.setOption(optionLast);
						}
					});

		}

		//上个月
		function lastMonth() {
			var currentDate = document.getElementById('date').innerHTML;
			var value = currentDate.split('-');
			if (month === 1) {
				fullYear = parseInt(value[0]) - 1;
				month = 12;
			} else {
				month = parseInt(value[1]) - 1;
			}
			var str = fullYear + '-' + month;
			getVirtulData(fullYear, month);
			optionLast = {
				calendar : [ {
					range : str,
				} ],
				series : [ {
					data : lunarData
				}, {
					data : heatmapData
				} ]
			}
			document.getElementById('date').innerHTML = str;
			myChart.setOption(optionLast);
		}

		//下个月
		function nextMonth() {
			var currentDate = document.getElementById('date').innerHTML;
			var value = currentDate.split('-');
			if (month === 12) {
				fullYear = parseInt(value[0]) + 1;
				month = 1;
			} else {
				month = parseInt(value[1]) + 1;
			}
			var str = fullYear + '-' + month;
			getVirtulData(fullYear, month);
			optionLast = {
				calendar : [ {
					range : str,
				} ],
				series : [ {
					data : lunarData
				}, {
					data : heatmapData
				} ]
			}
			document.getElementById('date').innerHTML = str;
			myChart.setOption(optionLast);
		}
	</script>
</body>
</html>