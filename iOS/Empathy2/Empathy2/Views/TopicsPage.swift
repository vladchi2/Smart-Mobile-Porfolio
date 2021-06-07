//
//  TopicsPage.swift
//  Empathy2
//
//  Created by Vlad on 15/04/2021.
//

import SwiftUI

struct TopicsPage: View {
    var body: some View {
        NavigationView{
            ZStack{
            Background()
            
            
                VStack(spacing:30){
                    Text("Topics")
                        .frame(width: 360, height: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, alignment: .center)
                        .background(LinearGradient(gradient: Gradient(colors: [Color.black,.black, Color.gray]), startPoint: .top, endPoint: .bottom))
                        .cornerRadius(15)
                        .foregroundColor(.blue)
                        .font(.largeTitle)
                    Text("Choose the prefered topic")
                    Spacer()
                    TopicHStack(topic1: "Depression", topic2: "Alcohol")
                    TopicHStack(topic1: "Depression", topic2: "Alcohol")
                    TopicHStack(topic1: "Depression", topic2: "Alcohol")
                    TopicHStack(topic1: "Depression", topic2: "Alcohol")
                    
                 
                    Spacer()
                }
            }
            
        }
    }
}

struct TopicsPage_Previews: PreviewProvider {
    static var previews: some View {
        TopicsPage()
    }
}

struct Background: View {
    
    
    var body: some View {
        
        
        
        LinearGradient(gradient: Gradient(colors: [.black, .gray, .white, .blue]),
                       startPoint: .top,
                       endPoint: .bottom)
            .edgesIgnoringSafeArea(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
    }
}

struct TopicHStack: View {
    
    var topic1: String
    var topic2: String
    
    var body: some View {
        HStack(spacing: 40){
            Text(topic1)
                .frame(width: 160, height: 120, alignment: .center)
                .background(BackgroundTopicsLeft())
                .cornerRadius(20)
                .foregroundColor(.white)
                .font(.title)
            Text(topic2)
                .frame(width: 160, height: 120, alignment: .center)
                .background(BackgroundTopicsRight())
                .cornerRadius(20)
                .foregroundColor(.white)
                .font(.title)
        }    }
}
