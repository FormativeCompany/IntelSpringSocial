package test.org.springsocial.intel.types;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;

class Res implements HttpOutputMessage {
	ByteArrayOutputStream bos = new ByteArrayOutputStream();

	@Override
	public HttpHeaders getHeaders() {
		return new HttpHeaders();
	}

	@Override
	public OutputStream getBody() throws IOException {
		return bos;
	}

	@Override
	public String toString() {
		return new String(bos.toByteArray());
	}
}