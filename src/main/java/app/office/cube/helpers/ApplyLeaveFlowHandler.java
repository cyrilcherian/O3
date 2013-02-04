package app.office.cube.helpers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

public class ApplyLeaveFlowHandler extends AbstractFlowHandler {

	private static final String DEFAULT_URL = "/userleave/applylist";

	@Override
	public String handleExecutionOutcome(FlowExecutionOutcome outcome,
			HttpServletRequest request, HttpServletResponse response) {
		return DEFAULT_URL;
	}
	@Override
	public MutableAttributeMap createExecutionInputMap(
			HttpServletRequest request) {
		MutableAttributeMap map = super.createExecutionInputMap(request);
		return map;
	}
	@Override
	public String handleException(FlowException e, HttpServletRequest request,
			HttpServletResponse response) {
		if (e instanceof NoSuchFlowExecutionException) {
			return DEFAULT_URL;
		} else {
			throw e;
		}
	}

}
