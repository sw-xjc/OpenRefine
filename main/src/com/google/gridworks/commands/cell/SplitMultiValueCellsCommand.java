package com.google.gridworks.commands.cell;

  import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gridworks.commands.Command;
import com.google.gridworks.model.AbstractOperation;
import com.google.gridworks.model.Project;
import com.google.gridworks.operations.cell.MultiValuedCellSplitOperation;
import com.google.gridworks.process.Process;

public class SplitMultiValueCellsCommand extends Command {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Project project = getProject(request);
            
            String columnName = request.getParameter("columnName");
            String keyColumnName = request.getParameter("keyColumnName");
            String separator = request.getParameter("separator");
            String mode = request.getParameter("mode");
            
            AbstractOperation op = new MultiValuedCellSplitOperation(columnName, keyColumnName, separator, mode);
            Process process = op.createProcess(project, new Properties());
            
            performProcessAndRespond(request, response, project, process);
        } catch (Exception e) {
            respondException(response, e);
        }
    }
}