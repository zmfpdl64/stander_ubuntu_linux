package stander.stander.service;

import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Entity.Sit;
import stander.stander.repository.JpaSitRepository;

import java.util.List;

@Transactional
public class SitService {

    private JpaSitRepository sitRepository;

    public SitService(JpaSitRepository sitRepository) {
        this.sitRepository = sitRepository;
    }

    public void use(Sit sit) {
        sitRepository.use(sit);
    }

    public void exit(Sit sit) {
        sitRepository.exit(sit.getSit_id());
    }

    public List<Sit> findAll() {
        return sitRepository.findAll();
    }
}
