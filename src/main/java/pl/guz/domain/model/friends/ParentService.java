package pl.guz.domain.model.friends;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@AllArgsConstructor
class ParentService {

    private ParentRepository parentRepository;

    @PostConstruct
    public void init() {
        Parent parent = parentRepository.findBy(1L);
        ParentValue parentValue = parentRepository.findValue(1L);
        log.info("Found parent: {}", parent);
        log.info("Found parent value: {}", parentValue);
    }

}
