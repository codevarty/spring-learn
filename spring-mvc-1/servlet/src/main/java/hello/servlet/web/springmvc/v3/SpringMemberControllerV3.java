package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
  private MemberRepository memberRepository = MemberRepository.getInstance();

//  @RequestMapping(value = "/new-form", method = RequestMethod.GET) // GET인 경우에만 호출
  @GetMapping("/new-form")
  public String newForm() {
    return "new-form"; // return "new-form" == return ModelAndView("new-form")
  }

//  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @PostMapping("/save")
  String save(
      @RequestParam("username") String username, // request.getParameter("username")
      @RequestParam("age") int age,
      Model model) {

    Member member = new Member(username, age);
    memberRepository.save(member);

    model.addAttribute("member", member); // ModelAndView.addObject("member", member)
    return "save-result";
  }

//  @RequestMapping(method = RequestMethod.GET) // "/springmvc/v2/members"의 경로가 된다.
  @GetMapping
  public String members(Model model) {
    List<Member> members = memberRepository.findAll();
    model.addAttribute("members", members);

    return "members";
  }

}
