$(function() {
	loadTree();
	// 添加数据对话框
	$('#insertDlg').dialog({
		title : '添加数据',
		width : 350,
		height : 260,
		closed : true,
		modal : true,
		buttons : [ {
			text : '保存',
			handler : function() {
				// 访问后台数据
				insertData();
			}
		}, {
			text : '关闭',
			handler : function() {
				// 关闭对话框
				$("#insertDlg").dialog('close');
			}
		} ]
	});
	// 修改数据对话框
	$('#updateDlg').dialog({
		title : '修改数据',
		width : 350,
		height : 260,
		closed : true,
		modal : true,
		buttons : [ {
			text : '保存',
			handler : function() {
				// 访问后台数据
				updateData();
			}
		}, {
			text : '关闭',
			handler : function() {
				// 关闭对话框
				$("#updateDlg").dialog('close');
			}
		} ]
	});
	
});

/**
 * 加载两侧菜单
 */
function loadTree() {
	$.ajax({
		type : 'POST',
		url : 'manager/regionlist',
		dataType : 'json',
		success : function(rtn) {
			disposeTree(rtn);
		}
	});
}
/**
 * 处理左侧菜单
 */
function disposeTree(data) {
	// 加载左侧菜单
	$('#tt').tree({
		data : data,
		onContextMenu : function(e, node) {
			e.preventDefault();
			var rowData = $('#grid').datagrid('getRows')[0];
			if (rowData != null) {
				// 找到菜单项
				var item = $('#mm').menu('findItem', '删除');
				if (1 == rowData.is_parent) {
					if (item) {
						// 移除菜单项
						$('#mm').menu('removeItem', item.target);
					}
				} else {
					if (item == null) {
						// 追加一个顶部菜单
						$('#mm').menu('appendItem', {
							text : '删除',
							iconCls : 'icon-cut',
							onClick : function(item) {
								deleteData(rowData.id);
							}
						});
					}
				}
				// 显示快捷菜单
				$('#mm').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			} else {
				$.messager.alert("提示", "请选中后操作", 'warning');
			}
		},
		onClick : function(node) {
			// 显示子菜单到datagrid
			loadDataGrid(node.id);
		}
	});
}

/**
 * 加载表格数据 menuid：菜单id
 */
function loadDataGrid(region_id) {
	$('#grid').datagrid({
		url : 'manager/findManagerByRegionId?region_id=' + region_id,
		columns : [ [ {
			field : 'manager_id',
			title : '塘长编号',
			width : 100
		}, {
			field : 'manager_name',
			title : '塘长姓名',
			width : 100
		}, {
			field : 'manager_title',
			title : '职务',
			width : 200
		}, {
			field : 'manager_contact1',
			title : '联系方式1',
			width : 100,
		}, {
			field : 'manager_contact2',
			title : '联系方式2',
			width : 100,
		}
		] ],
		loading : true,
		striped : true,
		rownumbers : true,
		singleSelect : true
	});
	$('#grid').datagrid({
		toolbar : [ {
			iconCls : 'icon-add',
			text : '添加',
			handler : function() {
				// 打开添加窗口
				var selected = $('#tt').tree('getSelected');
				var id = selected.id;
				var text=selected.text;
				var newData = {'region_id':id,'region_name':text};
				$("#insertDlg").dialog('open');
				$('#insertForm').form('load',newData);
			}
		}, '-', {
			iconCls : 'icon-save',
			text : '修改',
			handler : function() {
				var rowData = $('#grid').datagrid('getSelected');
				if (null != rowData) {
					// 打开修改窗口
					$('#updateDlg').dialog('open');
					// 填充后台数据
					$('#updateForm').form('load', rowData);
				} else {
					$.messager.alert("提示", "请选中要修改的行", 'error');
				}
			}
		}, '-', {
			iconCls : 'icon-cut',
			text : '删除',
			handler : function() {
				// 删除
				var selected = $('#grid').datagrid('getSelected');
				del(selected);

			}
		} ],
		onDblClickRow : function(rowIndex, rowData) {
			// 打开修改窗口
			$('#updateDlg').dialog('open');
			$('#updateForm').form('load', rowData);
		}
	});
}

/**
 * 添加数据
 */
function insertData() {
	//var rowData = $('#grid').datagrid('getData').rows[0];
	// 提交添加数据的表单
	var formData = $('#insertForm').serializeJSON();
	//formData.pid = rowData.menuid;
	//formData.is_parent = rowData.is_parent;
	$.ajax({
		type : 'POST',
		url : 'manager/manageradd',
		data : formData,
		dataType : 'json',
		success : function(data) {
			$.messager.alert("提示", data.msg, 'info', function() {
				if (data.status == 200) {
					// 刷新表格数据
					$('#grid').datagrid('reload');
					$('#insertDlg').dialog('close');
					// 清除表单数据
					$('#insertForm').form('clear');
				}
			});
		}
	});
}

/**
 * 删除数据
 */
function del(selected) {
	$.messager.confirm("确认", "确认要删除吗？", function(yes) {
		if (yes) {
			$.ajax({
				url : 'manager/managerdelete',
				data : selected,
				dataType : 'json',
				type : 'post',
				success : function(rtn) {
					$.messager.alert("提示", rtn.msg, 'info', function() {
						// 刷新表格数据
						$('#grid').datagrid('reload');
					});
				}
			});
		}
	});
}
/**
 * 修改数据
 */
function updateData() {
	var formData = $('#updateForm').serializeJSON();
	$.ajax({
		type : 'POST',
		url : 'manager/managerupdate',
		data : formData,
		dataType : 'json',
		success : function(data) {
			$.messager.alert("提示", data.msg, 'info', function() {
				if (data.status == 200) {
				// 刷新表格数据
				$('#grid').datagrid('reload');
				// 关闭对话框
				$('#updateDlg').dialog('close');
				// 清除表单数据
				$('#updateForm').form('clear');
				}
			});
		}
	});
}