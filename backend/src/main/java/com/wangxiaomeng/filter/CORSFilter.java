package com.wangxiaomeng.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CORSFilter", value = "/*")
public class CORSFilter implements Filter {
    private static Logger logger = (Logger)LogManager.getLogger("initlog");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("enter CORSFilter init!");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        logger.info(request.getMethod());
        logger.info("start CORSFilter doFilter. The URL is " + request.getRequestURL());
        logger.info("use nginx to solve the CORS problem");
        /*
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Accept, Origin, User-Agent, Content-Range, Content-Disposition, Content-Description");
        if("OPTIONS".equals(request.getMethod())){
            return;
        }
        */
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("start CORSFilter destroy");
    }
}
