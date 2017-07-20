package Servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class fileServlet
 */
@WebServlet("/fileServlet")
public class fileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		System.out.println("欢迎来到fileServlet");
		String methodname = request.getParameter("method");
		if (methodname.equals("upload")) {
			upload(request,response);
		}
		if (methodname.equals("downList")) {
			downList(request,response);	
			}
		if (methodname.equals("down")) {
			down(request,response);	
		}
		
	}
	
	private void upload(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("欢迎来到fileServlet--upload");
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(30*1024*1024);
			upload.setSizeMax(8*1024*1024);
			upload.setHeaderEncoding("UTF-8");
			if(upload.isMultipartContent(request))
			{
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem fileItem : list) {
					if (fileItem.isFormField()) {

					}
					else {

						String id = UUID.randomUUID().toString();
						String name=id+'#'+fileItem.getFieldName();

						String path = request.getServletContext().getRealPath("/upload");

						java.io.File file=new java.io.File(path, name);
						System.out.println(file.getAbsolutePath());
						fileItem.write(file);
						fileItem.delete();
					}						
					}					
				}
				
			else {
				System.out.println("当前不说文件表单");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void downList(HttpServletRequest request, HttpServletResponse response)
	{
		
		System.out.println("欢迎来到fileServlet--downList");
			try {Map<String, String> fileNames=new LinkedHashMap();
			String path = request.getServletContext().getRealPath("/upload");
			System.out.println(path);
			java.io.File file=new java.io.File(path);
			String[] subfilelist = file.list();
			System.out.println(subfilelist);
	
			if (subfilelist!=null && subfilelist.length>=1) {
				for (int i = 1; i <= subfilelist.length; i++) {
					String fullname = subfilelist[i-1];
					System.out.println(fullname);
					String tempsubname = fullname.substring(fullname.lastIndexOf("#")+1);
					String subname = tempsubname+i;
					fileNames.put(fullname, subname);
				}
						
			}
			request.setAttribute("fileNames", fileNames);
			request.getRequestDispatcher("downlist.jsp").forward(request, response);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	
	
	private void down(HttpServletRequest request, HttpServletResponse response)
	{
		
		System.out.println("欢迎来到fileServlet--down");
		try {
			String filename = request.getParameter("fileName");
			filename = new String(filename.getBytes("ISO8859-1"),"UTF-8");
			System.out.println(filename);
			String path = request.getServletContext().getRealPath("/upload");
			java.io.File file=new java.io.File(path, filename);
			FileInputStream in = new FileInputStream(file);
			response.setHeader("content-disposition", "attachment;fileName=" + filename);
			OutputStream out = response.getOutputStream();
		
			byte[] bf=new byte[1024];
			int len=-1;
			while ((len=in.read(bf))!=-1) {
				out.write(bf);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	 System.out.println( "更");

	}
}
