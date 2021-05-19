/*
 * try-finally 보다는 try-with-resources를 사용하라
 * : InputStream, OutputStream, java.sql.Connection과 같은 것들은 close 메서드를 통해
 * 직접 닫아주어야 한다. 이때 주로 try-finally 구조를 많이 사용한다.
 * */

import java.io.*;

public class Item09 {

    // 나쁘지 않다. 하지만 자원을 하나 더 사용한다면?
    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }

    // 자원을 하나 더 사용하는 예
    /*
     * 예외는 try블록과 finally블록 모두에서 발생할 수 있다.
     * 물리적인 문제로 인해 예외가 발생한다면, readLine도 실패할 것이고, close도 실패할 것이다.
     * 디버깅이 매우 힘들어진다.
     * : 이 문제를 try-with-resources가 모두 해결해주었다.*/
    static void copy(String src, String dst) throws IOException {
        File file;
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[255];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    /*
     * 많은 라이브러리의 클래스와 인터페이스가 이미 AutoCloseable을 구현하거나 확장해뒀다.
     * 만약 닫아야하는 자원을 뜻하는 클래스를 작성한다면 AutoCloseable을 반드시 구현할 것.*/
    static String firstLineOfFile2(String path, String defaltVal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
        // catch문도 사용 가능
        catch (IOException e) {
            return defaltVal;
        }
    }

    // 복수의 자원을 이용할 땐 더욱 짧고 매력적
    static void copy2(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[255];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }
}
