package vn.edu.hcmute.fit.web3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Debug (GET): action = " + action);
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Debug: in ra action từ request
        String action = request.getParameter("action");
        System.out.println("Debug (POST): action = " + action);

        List<String> errors = new ArrayList<>();

        // 1. Nhận dữ liệu từ form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String hearAbout = request.getParameter("hearAbout");
        boolean receiveCds = (request.getParameter("receiveCds") != null);
        boolean receiveEmails = (request.getParameter("receiveEmails") != null);
        String contactBy = request.getParameter("contactBy");

        // 2. Validate dữ liệu
        if (firstName == null || firstName.trim().isEmpty()) {
            errors.add("Tên không được để trống.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            errors.add("Họ không được để trống.");
        }
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email không được để trống.");
        }
        try {
            if (dob != null && !dob.trim().isEmpty()) {
                LocalDate.parse(dob);
            }
        } catch (DateTimeParseException e) {
            errors.add("Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng YYYY-MM-DD.");
        }

        // 3. Forward tới JSP phù hợp
        String url;
        if (!errors.isEmpty()) {
            // Có lỗi -> quay lại index.jsp + giữ dữ liệu nhập
            request.setAttribute("errors", errors);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("dob", dob);
            request.setAttribute("hearAbout", hearAbout);
            request.setAttribute("receiveCds", receiveCds);
            request.setAttribute("receiveEmails", receiveEmails);
            request.setAttribute("contactBy", contactBy);

            url = "/index.jsp";
        } else {
            // Không lỗi -> tạo user + chuyển tới thanks.jsp
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setDob(dob);
            user.setHearAbout(hearAbout);
            user.setReceiveCds(receiveCds);
            user.setReceiveEmails(receiveEmails);
            user.setContactBy(contactBy);

            request.setAttribute("user", user);
            url = "/thanks.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
