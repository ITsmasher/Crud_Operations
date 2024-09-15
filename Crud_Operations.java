import java.util.Scanner;
import java.sql.*;

public class Crud_Operations {
	 static Scanner sc = new Scanner(System.in);
	 Connection con = null;
	 PreparedStatement ps = null;
	 ResultSet rs = null;
	 int eid;
	 String ename;
	 double sal;
	 int dept;
	 String address;
	String getConnection = "jdbc:mysql://localhost:3306/demo?user=root&password=778046@upi";
	String insert = "insert into emp values(?,?,?,?,?);";
	String update = "update emp set ename=? where eid=?;";
	String find = "select* from emp where eid=?;";
	String delete = "delete from emp where eid=?;";
	String fetch = "select* from emp";
	public static void main(String[] args) {

		
		Crud_Operations obj = new Crud_Operations();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			obj.con = DriverManager.getConnection(obj.getConnection);
			
			while(true) {
				System.out.println("Enter the operation Name that you want to perform 👇");
				System.out.println(" ➡️ Insert\n ➡️ Update\n ➡️ Find\n ➡️ Delete\n ➡️ Fetch\n ➡️ exit");
				String input = sc.nextLine();
				if(input.toLowerCase().equals("insert")) {
					obj.insert();
					System.out.println("==================================================");
				}else if(input.toLowerCase().equals("update")) {
					obj.update();
					System.out.println("==================================================");
				}else if(input.toLowerCase().equals("find")) {
					obj.find();
					System.out.println("==================================================");
				}else if(input.toLowerCase().equals("delete")) {
					obj.delete();
					System.out.println("==================================================");
				}else if(input.toLowerCase().equals("fetch")) {
					obj.fetch();
					System.out.println("===========================================================================");
				}else if(input.toLowerCase().equals("exit")) {
					obj.exit();
				}else {
					System.out.println();
					System.out.println("❌✖ Invalid Operation! please enter valid input. ✖️❌");
					System.out.println("=====================================================");
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(obj.rs != null) {
				try {
					obj.rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				
			if(obj.ps != null) {
				try {
					obj.ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				
			if(obj.con != null) {
				try {
					obj.con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void insert() {
        System.out.println("Insert Employee id:");
        eid = sc.nextInt();
        sc.nextLine();
        System.out.println("Insert Employee Name:");
        ename = sc.nextLine();
        System.out.println("Insert Employee Salary:");
        sal = sc.nextDouble();
        sc.nextLine();
        System.out.println("Insert Employee Department number:");
        dept = sc.nextInt();
        sc.nextLine();
        System.out.println("Insert Employee Address:");
        address = sc.nextLine();
		try {
			ps = con.prepareStatement(insert);
			ps.setInt(1, eid);
			ps.setString(2, ename);
			ps.setDouble(3, sal);
			ps.setInt(4, dept);
			ps.setString(5, address);
			int rowInserted = ps.executeUpdate();
			if(rowInserted > 0) {
				System.out.println(">>>>>>>>>>Insertion Completed 👍 <<<<<<<<<<");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		System.out.println("You can Update employee Name using eid");
		System.out.println();
		System.out.println("Enter the employee Name:");
		ename = sc.nextLine();
		System.out.println("Enter the employee id:");
		eid = sc.nextInt();
		try {
			ps = con.prepareStatement(update);
			ps.setString(1, ename);
			ps.setInt(2, eid);
			int rowUpdated = ps.executeUpdate();
			if(rowUpdated > 0) {
				System.out.println(">>>>>>>>>>Updation Completed 👍 <<<<<<<<<<");
			}
			sc.nextLine();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void find() {
		System.out.println("Find the employee details using employee id:");
		System.out.println();
		System.out.println("Enter the employee id:");
		eid = sc.nextInt();
		try {
			ps = con.prepareStatement(find);
			ps.setInt(1, eid);
			rs = ps.executeQuery();
			while(rs.next()) {
                System.out.println("Eid: "+rs.getInt(1)+" | Name: "+ rs.getString(2) + " | Salary: " + rs.getDouble(3) + " |Department: " + rs.getInt(4) + " |Address: " + rs.getString(5));
                System.out.println();
			}
			sc.nextLine();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void delete() {
		System.out.println("Enter the employee id to delete the record:");
		eid = sc.nextInt();
		try {
			ps = con.prepareStatement(delete);
			ps.setInt(1, eid);
			int rowDeleted = ps.executeUpdate();
			sc.nextLine();
			if(rowDeleted > 0) {
				System.out.println(">>>>>>>>>>Deletion Completed 👍 <<<<<<<<<<");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fetch() {
		try {
			ps = con.prepareStatement(fetch);
			rs = ps.executeQuery();
			System.out.println("The Fetched Recodrs are: 👇👇");
			System.out.println();
			while(rs.next()) {
				eid = rs.getInt(1);
				ename = rs.getString(2);
				sal = rs.getDouble(3);
				dept = rs.getInt(4);
				address = rs.getString(5);
				System.out.println("Eid: "+eid+" | Name: "+ename+" | Salary: "+sal+" | Department: "+dept+" | address: "+address);
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void exit() {
		System.out.println(">>>>>>>>>>Application Closed 🔒<<<<<<<<<<");
		sc.close();
		System.exit(0);
	}
	
}
