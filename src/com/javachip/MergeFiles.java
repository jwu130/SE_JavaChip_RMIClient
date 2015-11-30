package com.javachip;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javachip.merge.FileMerge;
import com.javachip.util.Util;

/**
 * Servlet implementation class MergeFiles
 */
@WebServlet("/MergeFiles/*")
public class MergeFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String file1 = (String) request.getParameter("file1");
		String file2 = (String) request.getParameter("file2");

		String temp = request.getParameter("keepRepeats");
		boolean saveRepeats;
		if (temp.equalsIgnoreCase("true"))
			saveRepeats = true;
		else
			saveRepeats = false;

		File parentDir = new File(System.getProperty("user.dir"));
		String mergeFileName = String.valueOf(new Date().getTime());
		
		if (parentDir.exists()) {
			FileMerge fileMerge = new FileMerge();
			fileMerge.mergeFiles(parentDir, file1, file2);
			
			if(!saveRepeats){
				fileMerge.setRepeats(null);
			}
			
			fileMerge.saveAndMerge(mergeFileName);

			Util.setRequestAttr(request, file1, "mergedFile", mergeFileName);
		}		
	
		try {
			getServletConfig().getServletContext().getRequestDispatcher("/MergeComplete.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("<h1> Hello. The server could not get the page you requested. </h1>");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}