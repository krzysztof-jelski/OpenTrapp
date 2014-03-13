package com.github.mpi.time_registration.application;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.mpi.time_registration.domain.EmployeeID;
import com.github.mpi.time_registration.domain.ProjectName;
import com.github.mpi.time_registration.domain.WorkLog;
import com.github.mpi.time_registration.domain.WorkLogEntry;
import com.github.mpi.time_registration.domain.WorkLogEntry.EntryID;
import com.github.mpi.time_registration.domain.WorkLogEntryRepository;
import com.github.mpi.time_registration.domain.Workload;
import com.github.mpi.time_registration.domain.time.Month;

@Controller
@RequestMapping(method   = GET,
                value    = "/endpoints/v1",
                produces = "application/json")
public class ReportingEndpoint {

    @Autowired
    private WorkLogEntryRepository repository;
    
    @RequestMapping("/projects/{projectName}/work-log/entries")
    public @ResponseBody WorkLogJson projectWorkLog(@PathVariable String projectName){

        WorkLog workLog = repository.loadAll().forProject(new ProjectName(projectName));
        return jsonResponse(workLog);
    }

    @RequestMapping("/employee/{employeeID}/work-log/entries")
    public @ResponseBody WorkLogJson employeeWorkLog(@PathVariable String employeeID){

        WorkLog workLog = repository.loadAll().forEmployee(new EmployeeID(employeeID));
        return jsonResponse(workLog);
    }
    
    @RequestMapping("/calendar/{year}/{month}/work-log/entries")
    public @ResponseBody WorkLogJson monthWorkLog(@PathVariable String year, @PathVariable String month){
        
        Month m = Month.of(String.format("%s/%s", year, month));
        
        WorkLog workLog = repository.loadAll()
                .after(m.firstDay())
                .before(m.lastDay());
        
        return jsonResponse(workLog);
    }

    private WorkLogJson jsonResponse(WorkLog workLog) {
        List<WorkLogEntryJson> items = new ArrayList<ReportingEndpoint.WorkLogEntryJson>();
        for (WorkLogEntry entry : workLog) {
            items.add(new WorkLogEntryJson(entry.id(), entry.workload(), entry.projectName(), entry.employee()));
        };
        return new WorkLogJson(items);
    }

    @JsonAutoDetect(fieldVisibility=Visibility.ANY)
    class WorkLogJson {

        List<WorkLogEntryJson> items;

        WorkLogJson(List<WorkLogEntryJson> items) {
            this.items = items;
        }
    }
    
    @JsonAutoDetect(fieldVisibility=Visibility.ANY)
    class WorkLogEntryJson {

        String link, id, workload, projectName, employee;
        
        WorkLogEntryJson(EntryID id, Workload workload, ProjectName projectName, EmployeeID employee) {
            this.id = id.toString();
            this.workload = workload.toString();
            this.projectName = projectName.toString();
            this.employee = employee.toString();
            this.link = String.format("/endpoints/v1/work-log/entries/%s", id);
        }
    }
}
