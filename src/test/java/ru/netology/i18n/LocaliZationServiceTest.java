package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;


public class LocaliZationServiceTest {
    LocalizationServiceImpl localiZationService = new LocalizationServiceImpl();
    @Test
    public void localizationRussia() {
        String expected = "Добро пожаловать";

        String localMes = localiZationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expected, localMes);
    }

    @ParameterizedTest
    @EnumSource(Country.class)
    public void localizationOtherCountry(Country country) {
        String expected = "Welcome";

        String localMes = localiZationService.locale(country);

        Assertions.assertEquals(expected, localMes);
    }
}