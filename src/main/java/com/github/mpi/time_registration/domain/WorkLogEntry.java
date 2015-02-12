package com.github.mpi.time_registration.domain;

import java.util.Date;

import com.github.mpi.time_registration.domain.time.Day;

public class WorkLogEntry {

    private final EntryID id;
    private final EmployeeID employeeID;
    private final Day day;
    
    private Workload workload;
    private Iterable<ProjectName> projectNames;
    
    @SuppressWarnings("unused") // convinient for external systems 
    private Date createdAt = new Date();

    public WorkLogEntry(EntryID id, Workload workload, Iterable<ProjectName> projectNames, EmployeeID employeeID, Day day) {
        this.id = id;
        this.workload = workload;
        this.projectNames = projectNames;
        this.employeeID = employeeID;
        this.day = day;
    }

    public EntryID id() {
        return id;
    }

    public Workload workload() {
        return workload;
    }

    public Iterable<ProjectName> projectNames() {
        return projectNames;
    }

    public EmployeeID employee() {
        return employeeID;
    }

    public Day day() {
        return day;
    }
    
    public void updateWorkload(Workload newWorkload) {
        this.workload = newWorkload;
    }

    public void changeProjectsTo(Iterable<ProjectName> newProjects) {
        this.projectNames = newProjects;
    }

    @Override
    public boolean equals(Object x) {

        if (!(x instanceof WorkLogEntry)) {
            return false;
        }

        WorkLogEntry other = (WorkLogEntry) x;

        return this.id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static class EntryID {

        private final String id;

        public EntryID(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object x) {

            if (!(x instanceof EntryID)) {
                return false;
            }

            EntryID other = (EntryID) x;
            return id.equals(other.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        @Override
        public String toString() {
            return String.format("%s", id);
        }
    }

}
