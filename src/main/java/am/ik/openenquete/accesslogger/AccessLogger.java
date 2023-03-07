package am.ik.openenquete.accesslogger;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTrace.Principal;
import org.springframework.boot.actuate.trace.http.HttpTrace.Request;
import org.springframework.boot.actuate.trace.http.HttpTrace.Response;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.util.CollectionUtils;

public class AccessLogger implements HttpTraceRepository {

	private final Predicate<HttpTrace> filter;

	private final BiConsumer<StringBuilder, HttpTrace> logCustomizer;

	private final Logger log = LoggerFactory.getLogger("accesslog");

	public AccessLogger(Predicate<HttpTrace> filter, BiConsumer<StringBuilder, HttpTrace> logCustomizer) {
		this.filter = filter;
		this.logCustomizer = logCustomizer;
	}

	public AccessLogger(Predicate<HttpTrace> filter) {
		this(filter, (builder, trace) -> {
		});
	}

	public AccessLogger() {
		this(x -> true);
	}

	@Override
	public List<HttpTrace> findAll() {
		return List.of();
	}

	@Override
	public void add(HttpTrace trace) {
		if (!log.isInfoEnabled()) {
			return;
		}
		final Request request = trace.getRequest();
		final URI uri = request.getUri();
		if (!filter.test(trace)) {
			return;
		}
		final Response response = trace.getResponse();
		final Principal principal = trace.getPrincipal();
		final Long timeTaken = trace.getTimeTaken();
		final Map<String, List<String>> headers = request.getHeaders();
		final StringBuilder log = new StringBuilder();
		final String remoteAddress = request.getRemoteAddress();
		if (remoteAddress != null) {
			log.append("remote=").append(remoteAddress).append(" ");
		}
		if (principal != null) {
			log.append("user=").append(principal.getName()).append(" ");
		}
		log.append("ts=\"").append(trace.getTimestamp()).append("\" ");
		log.append("method=").append(request.getMethod()).append(" ");
		log.append("url=").append(uri).append(" ");
		log.append("status=").append(response.getStatus()).append(" ");
		final List<String> referer = headers.get("referer");
		if (!CollectionUtils.isEmpty(referer)) {
			log.append("referer=\"").append(referer.get(0)).append("\" ");
		}
		final List<String> userAgent = headers.get("user-agent");
		if (!CollectionUtils.isEmpty(userAgent)) {
			log.append("ua=\"").append(userAgent.get(0)).append("\" ");
		}
		if (timeTaken != null) {
			log.append("response_time=").append(timeTaken).append(" ");
		}
		this.logCustomizer.accept(log, trace);
		this.log.info(log.toString().trim());
	}

}
