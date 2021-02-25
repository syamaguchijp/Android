public class ExceptionSample {

    public static void main(String args[]) {
		Test t = new Test();
		try {
			t.execute();
		} catch (MyException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("finally");
		}
    }
}

class MyException extends Exception{
	MyException(String msg) {
		super(msg);
	}
}

class Test {
	void execute() throws MyException {
		throw new MyException("例外が発生しました！"); 
	}
}