package irepdata.service;

import irepdata.model.Idea;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 15.11.2016.
 */
@Service
public interface IdeaService {
    public List<Idea> TEMPORARY(Long id);
}
