<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <%-- <li class="active caidanindex">
                <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a href="${ctx }/index/indexShow?act_index=0"  target="mainIframe">
                    <i class="icon-home"></i>
                    <span>首页</span>
                </a>
            </li>            
            <li>
                <a class="dropdown-toggle" href="#" >
                    <i class="icon-group"></i>
                    <span>用户</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="${ctx }/admin/user"  target="mainIframe">用户列表</a></li>
                    <li><a href="${ctx }/register"  target="mainIframe">新用户</a></li>
                </ul>
            </li>
            <li>
                <a class="dropdown-toggle" href="#" >
                    <i class="icon-group"></i>
                    <span>安全管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="${ctx }/role"  target="mainIframe">角色管理</a></li>
                    <li><a href="${ctx }/pms"  target="mainIframe">权限管理</a></li>
                </ul>
            </li> --%>
        </ul>
    </div>
<!-- end sidebar -->
<script>
$(function () {

	initMenu();

// 获取菜单数据
function initMenu(){
	$.ajax({
        type: "POST",
        url: "${ctx }/readPms/getPmsForMenu",
        data: {},
        dataType: "json",
        success: function(data,textStatus){
        	createMenu(data);
        },
        error : function() {    
            alert("数据异常！");    
        } 
    });
}

var ctx = "${ctx }";

// 创建菜单
function createMenu(data){
	var htmlText = '';
	$.each(data,function(index,item){
		if(item.parent_code == '0'){//根节点
			if(item.children == '0'){// 没有子节点
				var url = ctx+item.access_uri;
				if(item.access_uri == '#'){
					url = '#'
				}
				if(index == 0){
					htmlText += ' <li class="active caidanindex">                                          '+
								'     <div class="pointer">                                                '+
								'         <div class="arrow"></div>                                        '+
								'         <div class="arrow_border"></div>                                 '+
								'     </div>                                                               '+
								'     <a href="'+url+'"  target="mainIframe">  '+
								'         <i class="icon-home"></i>                                        '+
								'         <span>'+item.pms_name+'</span>                      '+
								'     </a>                                                                 '+
								' </li>                                                                    ';
					
			    }else{
			    	htmlText += ' <li class="caidanindex">                                          '+
								'     <a href="'+url+'"  target="mainIframe">  '+
								'         <i class="icon-cog"></i>                                        '+
								'         <span>'+item.pms_name+'</span>                      '+
								'     </a>                                                                 '+
								' </li>                                                                    ';
		
			    }
			}else{
				//有子节点  再次遍历json数据找出子节点
				if(index == 0){
					htmlText += ' <li class="active">                                                                             '+
								'     <div class="pointer">                                                '+
								'         <div class="arrow"></div>                                        '+
								'         <div class="arrow_border"></div>                                 '+
								'     </div>                                                               '+
								'     <a class="dropdown-toggle" href="#" >                                        '+
								'         <i class="icon-cog"></i>                                               '+
								'         <span>'+item.pms_name+'</span>                                                        '+
								'         <i class="icon-chevron-down"></i>                                        '+
								'     </a>                                                                         '+
								'     <ul class="submenu">                                                         ';
					var pmsCode = item.pms_code;
					$.each(data,function(i,obj){
						if(obj.parent_code == pmsCode){
							var url = ctx+obj.access_uri;
							if(obj.access_uri == '#'){
								url = '#'
							}
							htmlText += '<li><a href="'+url+'"  target="mainIframe">'+obj.pms_name+'</a></li>';
						}
					});
					htmlText += '</ul> </li>';
				}else{
					htmlText += ' <li >                                                                             '+
								'     <a class="dropdown-toggle" href="#" >                                        '+
								'         <i class="icon-cog"></i>                                               '+
								'         <span>'+item.pms_name+'</span>                                                        '+
								'         <i class="icon-chevron-down"></i>                                        '+
								'     </a>                                                                         '+
								'     <ul class="submenu">                                                         ';
					var pmsCode = item.pms_code;
					$.each(data,function(i,obj){
						if(obj.parent_code == pmsCode){
							var url = ctx+obj.access_uri;
							if(obj.access_uri == '#'){
								url = '#'
							}
							htmlText += '<li><a href="'+url+'"  target="mainIframe">'+obj.pms_name+'</a></li>';
						}
					});
					htmlText += '</ul> </li>';
				}// end 有子节点 非0index
				
			}// end 有子节点
		}//根节点
	});//遍历json结束
	
	//alert(htmlText);
	$('#dashboard-menu').html(htmlText);
	
	
	//菜单单击事件  改变 右侧箭头  qiulngjie
	$("#dashboard-menu .caidanindex").click(function (e) {
	      //e.preventDefault();
		 delAndAddActiveClass($(this));
	 }); 

	/*$("#dashboard-menu > li > ul > li > a").click(function (e) {*/
	$("#dashboard-menu li").on('click','ul > li > a',function (e) {
		  var item = $(this).parent().parent().parent();
		  delAndAddActiveClass(item);
	}); 
	

	$("#dashboard-menu li").on('click','.dropdown-toggle',function (e) {
		 $(this).parent().find(".submenu").slideToggle('fast');
	});
	
}//end createMenu()


///  ***********************************************

function delAndAddActiveClass(doc){
	  $("#dashboard-menu li div[class='pointer']").remove();
	  var $item = doc.parent().children();
      $item.each(function (index, domEle) { 
      	if ($(this).hasClass("active")) {
      		$(this).removeClass("active");
      	}
      });
      doc.addClass("active");
      var htmlStr = doc.html();
	  var divStr = '<div class="pointer"><div class="arrow"></div><div class="arrow_border"></div></div>';
	  doc.html(divStr + htmlStr);
}

});
</script>