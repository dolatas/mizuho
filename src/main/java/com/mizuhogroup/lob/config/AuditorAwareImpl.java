package com.mizuhogroup.lob.config;

import com.mizuhogroup.lob.repository.OrderEntity;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<OrderEntity> {

    @Override
    public Optional<OrderEntity> getCurrentAuditor() {
        return Optional.empty();
    }
}
