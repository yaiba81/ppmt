package com.ppmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppmt.domain.Backlog;
import com.ppmt.domain.ProjectTask;
import com.ppmt.repositories.BacklogRepository;
import com.ppmt.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		//Exception: Project not found
		
		//PTs to be added to a specific project, project != null, BL exist
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		
		//set backlog to project task
		projectTask.setBacklog(backlog);
		
		//we want our project sequence to be like this: IDPRO-1 IDPRO-2 ..101 102
		Integer BacklogSequence = backlog.getPTSequence();	
		
		//update the backlog sequence
		BacklogSequence++;
		backlog.setPTSequence(BacklogSequence);
		//add the sequence to projectTask
		projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);		
		projectTask.setProjectIdentifier(projectIdentifier);
		
		//initialize priority when priority null
		if(projectTask.getPriority()==null || projectTask.getPriority()==0 ) {
			projectTask.setPriority(3);
		}
		//initialize status when status is null
		if(projectTask.getStatus()==""|| projectTask.getStatus()==null) {
			projectTask.setStatus("TO_DO");
		}
		return projectTaskRepository.save(projectTask);
	}
}
