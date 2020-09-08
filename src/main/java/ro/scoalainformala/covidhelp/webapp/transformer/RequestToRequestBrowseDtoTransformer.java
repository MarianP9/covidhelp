package ro.scoalainformala.covidhelp.webapp.transformer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ro.scoalainformala.covidhelp.webapp.domain.Request;
import ro.scoalainformala.covidhelp.webapp.dto.RequestBrowseDto;

@Component
public class RequestToRequestBrowseDtoTransformer {

    private final ModelMapper modelMapper;

    public RequestToRequestBrowseDtoTransformer(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public RequestBrowseDto transform(Request request) {
        RequestBrowseDto requestBrowseDto = new RequestBrowseDto();
        modelMapper.map(request, requestBrowseDto);
        return requestBrowseDto;
    }
}
