package bs.BookShop.Service.Impl;

import bs.BookShop.Model.City;
import bs.BookShop.Repository.CityRepository;
import bs.BookShop.Service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> findById(Long cityId) {
        return this.cityRepository.findById(cityId);
    }

    @Override
    public List<City> listAll() {
        return this.cityRepository.findAll();
    }
}
