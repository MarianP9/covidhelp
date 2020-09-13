package ro.scoalainformala.covidhelp.webapp.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.dto.RequestViewDto;

@Component
public class RequestToRequestViewDtoTransformer {

    private final ModelMapper modelMapper;

    public RequestToRequestViewDtoTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RequestViewDto transform(Request request) {

        RequestViewDto requestViewDto = new RequestViewDto();

        modelMapper.map(request, requestViewDto);
        requestViewDto.setRequestId(request.getId());
        requestViewDto.setRequesterId(request.getRequester().getId());
        requestViewDto.setRequestType(request.getType().getTypeName());
        requestViewDto.setDetails(request.getDetails());
        requestViewDto.setAddress(request.getRequester().getAddress());
        requestViewDto.setStartDate(request.getStartDate());
        requestViewDto.setEndDate(request.getEndDate());
        requestViewDto.setStatus(request.getStatus());
        requestViewDto.setRejectionReason(request.getRejectionReason());
        requestViewDto.setVolunteers(request.getVolunteers());

        return requestViewDto;
    }

}
