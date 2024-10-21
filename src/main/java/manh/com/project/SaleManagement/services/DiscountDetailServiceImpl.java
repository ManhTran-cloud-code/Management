package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.DiscountDetail;
import manh.com.project.SaleManagement.repositories.DiscountDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountDetailServiceImpl implements DiscountDetailService {

    @Autowired
    private DiscountDetailRepository discountDetailRepository;
    @Override
    public DiscountDetail saveDiscountDetail(DiscountDetail detail) {
        return (discountDetailRepository.saveDiscountDetail(detail)==0)?null:detail;
    }
}
