package ro.scoalainformala.covidhelp.webapp.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ro.scoalainformala.covidhelp.webapp.domain.RequestType;
import ro.scoalainformala.covidhelp.webapp.repository.RequestTypeRepository;

@Component
public class StringToRequestType implements Converter<String, RequestType> {
    @Autowired
    private RequestTypeRepository repository;

    @Override
    public RequestType convert(String s) {
        return repository.findById(Long.valueOf(s)).get();
    }
}
