package base.entity;

import base.util.StringUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UpperCaseConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String valor) {
		if (StringUtil.isNotEmpty(valor))
			return valor.toUpperCase();
		return valor;
	}

	@Override
	public String convertToEntityAttribute(String valor) {
		return valor;
	}

}
