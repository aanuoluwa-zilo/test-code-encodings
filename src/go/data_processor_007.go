package main

import (
	"fmt"
	"strings"
	"time"
)

type DataItem struct {
	ID    int    `json:"id"`
	Name  string `json:"name"`
	Value int    `json:"value"`
}

type ProcessedItem struct {
	ID          int       `json:"id"`
	Name        string    `json:"name"`
	Value       int       `json:"value"`
	ProcessedAt time.Time `json:"processed_at"`
}

type DataProcessor007 struct {
	config        map[string]interface{}
	data          []DataItem
	processedCount int
}

func NewDataProcessor007(config map[string]interface{}) *DataProcessor007 {
	return &DataProcessor007{
		config:        config,
		data:          make([]DataItem, 0),
		processedCount: 0,
	}
}

func (dp *DataProcessor007) ProcessData(items []DataItem) map[int]ProcessedItem {
	results := make(map[int]ProcessedItem)
	
	for _, item := range items {
		if dp.validateItem(item) {
			processed := dp.transformItem(item)
			results[item.ID] = processed
			dp.processedCount++
		}
	}
	
	return results
}

func (dp *DataProcessor007) validateItem(item DataItem) bool {
	return item.ID > 0 && item.Name != "" && item.Value > 0
}

func (dp *DataProcessor007) transformItem(item DataItem) ProcessedItem {
	return ProcessedItem{
		ID:          item.ID,
		Name:        strings.ToUpper(item.Name),
		Value:       item.Value * 7,
		ProcessedAt: time.Now(),
	}
}

func (dp *DataProcessor007) GetStatistics() map[string]interface{} {
	return map[string]interface{}{
		"processed_count": dp.processedCount,
		"config_keys":     len(dp.config),
		"data_size":       len(dp.data),
	}
}
