package com.itkon.navigationsystem.utils.on_sturtup;

import com.itkon.navigationsystem.model.BaseStation;
import com.itkon.navigationsystem.model.MobileStation;
import com.itkon.navigationsystem.repos.BaseStationRepository;
import com.itkon.navigationsystem.repos.MobileStationRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FillDbWithDummyData implements InitializingBean {

    private final BaseStationRepository baseStationRepository;
    private final MobileStationRepository mobileStationRepository;
    private final NumbersGenerator numbersGenerator;

    public FillDbWithDummyData(BaseStationRepository baseStationRepository, MobileStationRepository mobileStationRepository, NumbersGenerator numbersGenerator) {
        this.baseStationRepository = baseStationRepository;
        this.mobileStationRepository = mobileStationRepository;
        this.numbersGenerator = numbersGenerator;
    }

    @Override
    public void afterPropertiesSet() {
        for (int i = 0; i < 100; i++) {
            double randomValue = numbersGenerator.getRandomValue();
            BaseStation baseStation = new BaseStation(
                    UUID.randomUUID().toString(),
                    "test_name" + i,
                    randomValue,
                    numbersGenerator.getRandomValue(),
                    numbersGenerator.getRandomValue()
            );

            MobileStation mobileStation = new MobileStation(
                    UUID.randomUUID().toString(),
                    numbersGenerator.getRandomValue(),
                    numbersGenerator.getRandomValue()
            );
            baseStationRepository.save(baseStation);
            mobileStationRepository.save(mobileStation);
        }
    }

}