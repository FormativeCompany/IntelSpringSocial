package test.org.springsocial.intel.types;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

public final class Req implements HttpInputMessage {
	String s;

	public Req(String s) {
		this.s = s;
	}

	@Override
	public InputStream getBody() throws IOException {
		return new ByteArrayInputStream(s.getBytes());
	}

	@Override
	public HttpHeaders getHeaders() {
		return new HttpHeaders();
	}
}
