package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
  @Override
  public boolean supports(Object handler) {
    return (handler instanceof ControllerV4);
  }

  @Override
  public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
    // MemberFormControllerV4, MemberSaveControllerV4, MemberListControllerV4
    ControllerV4 controller = (ControllerV4) handler;

    Map<String, String> paramMap = createParamMap(request);
    HashMap<String, Object> model = new HashMap<>();

    // process input : paramMap, model
    String viewName = controller.process(paramMap, model); // return String type viewName
    ModelView mv = new ModelView(viewName); // ModelView constructor input : viewName
    mv.setModel(model); // model 세팅 model 값 process에서 변경 됨

    return mv; // return ModelView
  }

  // 모든 request 파라 미터를 조회해서 Map 저장헤서 반환
  private static Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String, String> paramMap = new HashMap<>();
    request.getParameterNames().asIterator()
        .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
    return paramMap;
  }
}
