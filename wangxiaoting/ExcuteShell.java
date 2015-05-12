package Shell;

public class ExcuteShell {
	public static void main(String[] args) {	
		execShell("sh /shell/pos.sh"); 
	}
	

	public static void execShell(String cmd) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				process.destroy();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void ConnectSSH(){
//		JSch jsch =new JSch();
	}
}
