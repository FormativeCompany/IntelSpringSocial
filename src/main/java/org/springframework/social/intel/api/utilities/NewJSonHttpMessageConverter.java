package org.springframework.social.intel.api.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

@SuppressWarnings({"unchecked","rawtypes"})
public final class NewJSonHttpMessageConverter extends MappingJacksonHttpMessageConverter {
	protected Class[][] param;
	protected Class[] type;
	protected TypeReference<?> ref;
	protected ObjectMapper om;
	
	private boolean logInOut = false;
	
	public NewJSonHttpMessageConverter() {
		om = new ObjectMapper();
		setObjectMapper(om);
	}

	public NewJSonHttpMessageConverter(TypeReference ref) {
		this.ref = ref;
		
		om = new ObjectMapper();
		setObjectMapper(om);
	}
	
	public NewJSonHttpMessageConverter(Class[] type, Class[][] param) {
		this.type =type;
		this.param = param;
		
		om = new ObjectMapper();
		setObjectMapper(om);
	}

	public NewJSonHttpMessageConverter(Class type, Class param) {
		this(new Class[]{type},new Class[][]{{param}});
	}

	public NewJSonHttpMessageConverter(Class type, Class[] param) {
		this(new Class[]{type},new Class[][]{param});
	}

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		OutputStream os = outputMessage.getBody();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
		JsonGenerator jsonGenerator =
				getObjectMapper().getJsonFactory().createJsonGenerator(bos, encoding);
		try {
			Field declaredField = getField();
			
			if(declaredField != null) {
			declaredField.setAccessible(true);
			if (declaredField.getBoolean(this)) {
				jsonGenerator.writeRaw("{} && ");
			}
			}
			this.getObjectMapper().writeValue(jsonGenerator, object);
			if(logInOut) {
				System.out.println("SENDING : "+new String(bos.toByteArray()));
			}
			os.write(bos.toByteArray());
		}
		catch (Exception ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}

	protected Field getField() throws NoSuchFieldException {
		Class<? extends MappingJacksonHttpMessageConverter> class1 = this.getClass();
		try {
			Field[] f = class1.getFields();
			for (Field field : f) {
				if(field.getName().equals("prefixJson")){
					return field;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		InputStream body = inputMessage.getBody();
		if(logInOut) {
			byte[] bytes = read(body);
			System.out.println("RECIEVED : "+new String(bytes));
			body = new ByteArrayInputStream(bytes);
		}
		if(ref != null) {
			try {
				return om.readValue(body, ref);
			}
			catch (JsonProcessingException ex) {
				throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
			}	
		}
		
		JavaType javaType = getJavaType(clazz);
		try {
			return om.readValue(body, javaType);
		}
		catch (JsonProcessingException ex) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}
	
	private byte[] read(InputStream body) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] temp = new byte[1024];
		while(true) {
			int i = body.read(temp);
			if(i == -1) break;
			bos.write(temp, 0, i);
		}
		return bos.toByteArray();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected JavaType getJavaType(Class<?> clazz) {
		if(type == null) {
			return super.getJavaType(clazz);
		}
		
		Class t;
		for (int i = 0; i < type.length; i++) {
			t = type[i];
			if(t.isAssignableFrom(clazz)) {
				return TypeFactory.parametricType(t, param[i]);
			}
		}
		return super.getJavaType(clazz);
	}
}
