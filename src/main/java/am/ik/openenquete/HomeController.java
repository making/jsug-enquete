package am.ik.openenquete;

import am.ik.openenquete.seminar.Seminar;
import am.ik.openenquete.seminar.SeminarRepository;
import am.ik.openenquete.session.Session;
import am.ik.openenquete.session.SessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

	private final SeminarRepository seminarRepository;

	private final SessionRepository sessionRepository;

	private final EnqueteProps props;

	public HomeController(SeminarRepository seminarRepository, SessionRepository sessionRepository,
			EnqueteProps props) {
		this.seminarRepository = seminarRepository;
		this.sessionRepository = sessionRepository;
		this.props = props;
	}

	@GetMapping("/")
	String index(Model model, @AuthenticationPrincipal EnqueteUser user,
			@PageableDefault(sort = "seminarDate", direction = Sort.Direction.DESC, size = 3) Pageable pageable) {
		Page<Seminar> openSeminars = seminarRepository.findBySeminarClosedIsNull(pageable);
		List<Session> sessions = sessionRepository.findBySpeakers(user.getGithub());

		model.addAttribute("openSeminars", openSeminars.getContent());
		model.addAttribute("sessions", sessions);

		// ADMIN only
		if (props.getAdminUsers().contains(user.getGithub())) {
			Page<Seminar> seminars = seminarRepository.findAll(pageable);
			model.addAttribute("seminars", seminars.getContent());
		}
		return "index";
	}

}
