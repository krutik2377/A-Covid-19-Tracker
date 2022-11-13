package com.AppProject.bean;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.AppProject.dao.CountryDao;

class CountryTest {

	@Test
	public void selectCountry() throws SQLException {
		CountryDao dao = new CountryDao();
		Country country = dao.selectCountry("Alberia");
		assertNotEquals(country,"");
		
	}
	
	@Test
	public void selectAllCountries() throws SQLException {
		CountryDao dao = new CountryDao();
		List<Country> country = dao.selectAllCountries();
		assertNotEquals(country,null);
		assertTrue(country.size()>0);	
	}
	
	@Test
	public void deleteCountry() throws SQLException{
		CountryDao dao = new CountryDao();
		Boolean val = dao.deleteCountry("Canada");
		Country country = dao.selectCountry("Canada");
		assertEquals(country,null);
	}
	
	@Test
	public void updateCountry() throws SQLException{
		CountryDao dao = new CountryDao();
		Country country = dao.selectCountry("Angola");
		country.setCountry("Bhutan");
		Boolean val = dao.updateCountry(country);
		assertTrue(val);
	}
	
	

}
