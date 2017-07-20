package Servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 * Servlet implementation class fileUploadServlet
 */
@WebServlet("/fileUploadServlet")
public class fileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
						System.out.println("filednname-- "+fileItem.getFieldName());
						System.out.println("content-- "+fileItem.getString());
						System.out.println("--------------------- ");
					}
					else {
						System.out.println("filednname-- "+fileItem.getFieldName());
						System.out.println("filename --"+fileItem.getName());
						System.out.println("content-- "+fileItem.getString());
						System.out.println("type --"+fileItem.getContentType());
						System.out.println("InputStream-- "+fileItem.getInputStream());
						System.out.println("--------------------- ");
						System.out.println("&&&&&&&&&&&&&&&&&& ");
						String id = UUID.randomUUID().toString();
						String name=id+'#'+fileItem.getFieldName();
						System.out.println(name);
						String path = request.getServletContext().getRealPath("/upload");
						System.out.println(path);
						java.io.File file=new java.io.File(path, name);
						System.out.println(file.getAbsolutePath());
						fileItem.write(file);
						fileItem.delete();
						System.out.println("&&&&&&&&&&&&&&&&&& ");

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
	
	
	
	
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
