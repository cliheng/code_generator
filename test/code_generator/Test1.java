package code_generator;

import java.io.IOException;
import java.sql.SQLException;

import com.mybdqn.pb.codetemplate.TableHandler;

public class Test1 {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		TableHandler handler = new TableHandler();
		String fieldName = handler.getFieldName("status", "");
		System.out.println(fieldName);
		
		String tableName = handler.getClassName("USER_type", "");
		System.out.println(tableName);
	}
}
