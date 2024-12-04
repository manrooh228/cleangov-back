package kz.cleangov.cleangov.domain.constructorClass;

import kz.cleangov.cleangov.domain.Investigations;

public class InvestigationWithProgress {
    private String id;
    private String name;
    private String description;
    private String level;
    private int progress; // Прогресс расследования

    // Конструктор
    public InvestigationWithProgress(Investigations investigation, int progress) {
        this.id = investigation.getId();
        this.name = investigation.getName();
        this.description = investigation.getDescription();
        this.level = investigation.getLevel();
        this.progress = progress;
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public int getProgress() {
        return progress;
    }
}
