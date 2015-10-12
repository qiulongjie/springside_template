package com.common.template.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 验证码校验器
 * @author qiulongjie
 *
 */
public class VerifyCodeFilter extends AccessControlFilter {
	public static final String VERIFY_ERROR_MSG = "verifyCode_error";
	
	private boolean isVerify = true;//是否开启验证码校验

	public void setIsVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		//设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute("isVerify", isVerify);
        
		// 判断验证码是否禁用 或不是表单提交（允许访问）
		if( isVerify == false || !"post".equalsIgnoreCase(req.getMethod()) ){
			return true;
		}
		String verifyCode = req.getParameter("verifyCode");
		String realCode = (String)req.getSession().getAttribute("verifyCode");  
		
		return codeVerify(verifyCode,realCode);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//如果验证码失败了，存储失败key属性
        request.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, VERIFY_ERROR_MSG);
		return true;
	}
	
	/**
	 * 验证验证码
	 * @param verifyCode
	 * @param realCode
	 * @return true 如果输入的验证码正确
	 */
	private boolean codeVerify(String verifyCode, String realCode) {
		if (verifyCode != null && realCode != null && realCode.equalsIgnoreCase(verifyCode)) {
			return true;
		}
		return false;
	}

}
