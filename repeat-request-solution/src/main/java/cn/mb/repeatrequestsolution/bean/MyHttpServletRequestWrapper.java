package cn.mb.repeatrequestsolution.bean;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *  自定义请求包装类
 * </p>
 *
 * @author: guohaibin
 * @createDate: 2021/1/13
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public MyHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream is = null;
        StringBuilder sb = null;
        try {
            is = request.getInputStream();
            sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n ; (n = is.read(b)) != -1;)
            {
                sb.append(new String(b, 0, n));
            }
        } finally {
            if(is != null) {
                is.close();
            }
        }
        body = sb.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
            @Override
            public int read() throws IOException {

                return bais.read();

            }

            @Override
            public void close() throws IOException {
                System.out.println(1);
                bais.close();
            }
        };
    }

    public String getBody() {
        return body;
    }

}
