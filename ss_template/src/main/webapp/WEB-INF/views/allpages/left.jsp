<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            
        </ul>
    </div>

    <!-- pre load bg imgs -->
<script type="text/javascript">
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
	
	var act_code = "${act_index}";

	// 创建菜单
	function createMenu(data){
		var htmlText = '';
		$.each(data,function(index,item){
			if(item.parent_code == '0'){//根节点
				if(item.children == '0'){// 没有子节点
					var url = '';
					if(item.access_uri == '#'){
						url = '#'
					}else{
						url = ctx+item.access_uri+'?act_index='+item.pms_code;
					}
					if(act_code === item.pms_code){
						htmlText += ' <li class="active caidanindex">                                          '+
									'     <div class="pointer">                                                '+
									'         <div class="arrow"></div>                                        '+
									'         <div class="arrow_border"></div>                                 '+
									'     </div>                                                               '+
									'     <a href="'+url+'" >  '+
									'         <i class="icon-home"></i>                                        '+
									'         <span>'+item.pms_name+'</span>                      '+
									'     </a>                                                                 '+
									' </li>                                                                    ';
						
				    }else{
				    	htmlText += ' <li class="caidanindex">                                          '+
									'     <a href="'+url+'" >  '+
									'         <i class="icon-cog"></i>                                        '+
									'         <span>'+item.pms_name+'</span>                      '+
									'     </a>                                                                 '+
									' </li>                                                                    ';
			
				    }
				}else{
					//有子节点  再次遍历json数据找出子节点
					if(act_code === item.pms_code){
						htmlText += ' <li class="active" pms_code="'+item.pms_code+'">    '+
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
								var url = '';
								if(obj.access_uri == '#'){
									url = '#'
								}else{
									url = ctx+obj.access_uri+'?act_index='+obj.parent_code;
								}
								
								htmlText += '<li><a href="'+url+'" >'+obj.pms_name+'</a></li>';
							}
						});
						htmlText += '</ul> </li>';
					}else{
						htmlText += ' <li pms_code="'+item.pms_code+'">                                                                             '+
									'     <a class="dropdown-toggle" href="#" >                                        '+
									'         <i class="icon-cog"></i>                                               '+
									'         <span>'+item.pms_name+'</span>                                                        '+
									'         <i class="icon-chevron-down"></i>                                        '+
									'     </a>                                                                         '+
									'     <ul class="submenu">                                                         ';
						var pmsCode = item.pms_code;
						$.each(data,function(i,obj){
							if(obj.parent_code == pmsCode){
								var url = '';
								if(obj.access_uri == '#'){
									url = '#'
								}else{
									url = ctx+obj.access_uri+'?act_index='+obj.parent_code;
								}
								htmlText += '<li><a href="'+url+'">'+obj.pms_name+'</a></li>';
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
		/* $("#dashboard-menu .caidanindex").click(function (e) {
		      //e.preventDefault();
			 delAndAddActiveClass($(this));
		 });  */

		/*$("#dashboard-menu > li > ul > li > a").click(function (e) {*/
		/* $("#dashboard-menu li").on('click','ul > li > a',function (e) {
			  var item = $(this).parent().parent().parent();
			  delAndAddActiveClass(item);
		});  */
		
		$("#dashboard-menu").children().each(function (index, domEle) { 
			var pms_code = $(this).attr("pms_code");
			if(act_code === pms_code){
				$(this).find(".submenu").slideDown("fast");
			}
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
	
	// 遍历li 设置选中箭头
	
	/* $("#dashboard-menu").children().each(function (index, domEle) { 
		if(act_index == index){
			$(this).addClass("active");
			var htmlStr = $(this).html();
		    var divStr = '<div class="pointer"><div class="arrow"></div><div class="arrow_border"></div></div>';
		    $(this).html(divStr + htmlStr);
		    var objSize = $(this).find("a[class='dropdown-toggle']").size();
		    if(objSize>0){
		    	$(this).find(".submenu").slideDown("fast");
		    }
		}
     }); */
	//菜单单击事件  改变 右侧箭头  qiulngjie
	/*   $("#dashboard-menu li").click(function (e) {
	      //e.preventDefault();
	      
	      var objSize = $(this).find("a[class='dropdown-toggle']").size();
	      if(objSize>0){
	    	  return ;
	      }
	      
	      $("#dashboard-menu li div[class='pointer']").remove();
	      
	      var $item = $(this).parent().children();
	      $item.each(function (index, domEle) { 
	      	if ($(this).hasClass("active")) {
	      		$(this).removeClass("active");
	      	}
	      });
	      
	      $(this).addClass("active");
	      
	      
	}); */
});
</script>    
    
<!-- end sidebar -->