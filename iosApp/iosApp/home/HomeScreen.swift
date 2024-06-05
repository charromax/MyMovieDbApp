import SwiftUI
import shared

struct HomeScreen: View {
    @StateObject var viewModel = HomeViewModel()
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 12), count: 2)
    
    var body: some View {
        NavigationStack {
            ScrollView {
                LazyVGrid(columns: gridColumns, spacing: 12) {
                    ForEach(viewModel.movies, id: \.id) { movie in
                        NavigationLink(value: movie) {
                            MovieGridItem(movie: movie)
                                .task {
                                if movie == viewModel.movies.last &&
                                    !viewModel.isLoading &&
                                    !viewModel.loadFinished {
                                    await viewModel.loadMovies()
                                }
                            }
                        }.buttonStyle(PlainButtonStyle())
                    }
                    if viewModel.isLoading {
                        Section(footer: ProgressView()) {}
                    }
                }
            }
            .navigationTitle("Movies")
            .navigationDestination(for: Movie.self) {movie in
                    DetailScreen(movie: movie)
            }
        }
        .task {
            await viewModel.loadMovies()
        }
    }
}

struct HomeScreen_Previews: PreviewProvider {
	static var previews: some View {
		HomeScreen()
	}
}
