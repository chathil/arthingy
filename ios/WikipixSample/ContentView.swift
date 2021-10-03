//
//  ContentView.swift
//  Wikipix
//
//  Created by Abdul Chathil on 05/08/21.
//

import SwiftUI
import wikipix // android module name convention
import SDWebImageSwiftUI
/**
 Doesn't work, the url is not assigned to url observable inside the viewmodel.
 */
struct ContentView: View {
  @ObservedObject var viewModel: ViewModel
  
  var body: some View {
    VStack {
      WebImage(url: URL(string: viewModel.url))
        .resizable().frame(width: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, height: /*@START_MENU_TOKEN@*/100/*@END_MENU_TOKEN@*/, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
      Text("el")
    }.onAppear(){
      viewModel.startObserving(title: "United States")
    }.onDisappear {
      viewModel.onDestroy()
    }
  }
}


