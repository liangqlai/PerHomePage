package com.lql.servlet;

import com.lql.bean.Info;
import com.lql.bean.Result;
import com.lql.service.DBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 小来
 */
@WebServlet("/v1/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");

        int userId = 0;
        String userIdText = request.getParameter("userid");
        if (userIdText != null) {
            try {
                userId = Integer.parseInt(userIdText);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Info info = DBService.find(userId);
        Result result = null;
        if (info != null) {
            result = new Result(0, "查询成功", info);
        } else {
            result = new Result(-1, "查询失败，不存在");
        }
        response.getWriter().append(result.toJSON());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
