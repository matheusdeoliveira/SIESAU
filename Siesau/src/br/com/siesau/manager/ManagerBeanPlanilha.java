package br.com.siesau.manager;

import java.io.File;
import java.io.FileOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.siesau.control.ImportaPlanilha;

@ManagedBean(name = "mbPlanilha")
@ViewScoped
public class ManagerBeanPlanilha {

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload(FileUploadEvent f) {

		UploadedFile uploadedFile = f.getFile();
		FileOutputStream outputStream;
		File file;
		try {

			if (uploadedFile != null) {

				System.out.println("File type: " + uploadedFile.getContentType());
				System.out.println("File name: " + uploadedFile.getFileName());
				System.out.println("File size: " + uploadedFile.getSize() + " bytes");

				file = new File("temp.xls");
				outputStream = new FileOutputStream(file);

				if (!file.exists()) {
					file.createNewFile();
				}

				outputStream.write(uploadedFile.getContents());
				outputStream.flush();
				outputStream.close();
				
				ImportaPlanilha.xlsInputpaciente(file);
				
				if(file.exists()){
					file.delete();
				}

				FacesContext.getCurrentInstance().addMessage("messages",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo enviado.", ""));

			} else {
				System.out.println("O Arquivo está vazio");

				FacesContext.getCurrentInstance().addMessage("messages",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "O arquivo está vazio.", ""));
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messages",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao enviar arquivo.", ""));
		}
	}

}