package club.banyuan.demo.authentication.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

public class DynamicResourceFilter extends AbstractSecurityInterceptor implements Filter {

  @Autowired
  private DynamicMetadataSource dynamicMetadataSource;

  @Autowired
  public void setAccessDecisionManager(DynamicAccessDecisionManager decisionManager) {
    super.setAccessDecisionManager(decisionManager);
  }

  @Override
  public Class<?> getSecureObjectClass() {
    return FilterInvocation.class;
  }

  @Override
  public SecurityMetadataSource obtainSecurityMetadataSource() {
    return dynamicMetadataSource;
  }


  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse,
        filterChain);

    InterceptorStatusToken token = super.beforeInvocation(filterInvocation);

    try {
      filterInvocation.getChain()
          .doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
    } finally {
      super.afterInvocation(token, null);
    }

  }
}
