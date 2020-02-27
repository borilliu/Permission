/**
 * esayui通用搜索
 */
$(function() {
	// 自动补全
	setReginBox();
	$('#inputtable').combobox(
			{
				prompt : '输入关键字后自动搜索',
				mode : 'remote',
				url : _url,// _url,_value已经在各自的js文件中定义
				valueField : _value,
				textField : _value,
				panelHeight : 'auto',
				panelMaxHeight : 150,
				editable : true,
				hasDownArrow : false,
				onBeforeLoad : function(param) {
					if (param == null || param.q == null
							|| param.q.replace(/ /g, '') == '') {
						var value = $(this).combobox('getValue');
						if (value) {// 修改的时候才会出现q为空而value不为空
							param.id = value;
							return true;
						}
						return false;
					}
				}
			});
	//回车事件绑定 搜索框是esayui动态生成的<input type="text" class="combo-text validatebox-text" autocomplete="off" style="width: 167px; height: 20px; line-height: 20px;">
    $('.combo-text').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
        	//$('#btnSearch').click();
        	reloadgrid();
        }
    });
	// 点击查询按钮
	$('#btnSearch').bind('click', function() {
		reloadgrid();
	});

	// 点击重置按钮
	$('#btnReset').bind('click', function() {
		$('#searchForm').form('clear');
	});
	
	// 点击导出按钮
	$('#btnImp').bind('click', function() {
		var formdata = new FormData($('#importForm')[0]);
		var regionId = formdata.get("region_id");
		$.ajax({
			url : "pond/ponddoImport",
			data : formdata,
			type : 'post',
			processData : false,
			contentType : false,
			dataType : 'json',
			success : function(data) {
				$.messager.alert('提示', data.msg,
						'info', function() {
							if (data.status==200) {
								$('#importForm').form('clear');
							}
						});
			}
		});
	});
	$('#btnExp').bind('click', function() {
		var formData = $('#exportForm').serializeJSON();
		// 下载文件
		$.download("pond/pondexport", formData);
	})	
	function reloadgrid() {
		// 把表单数据转换成json对象
		var formData = $('#searchForm').serializeJSON();
		$('#grid').datagrid('load', formData);
	}
	function setReginBox(){
		var url = "region/getregions?pid=83000";
		$.getJSON(url,function(json) {
			    $('#cc_region').combobox({
			        data: json,
			        valueField: 'region_id',
			        textField: 'region_name'
			    });
		});
	}
})