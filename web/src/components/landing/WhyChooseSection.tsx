import React from 'react';

export const WhyChooseSection: React.FC = () => {
  const pillars = [
    {
      icon: "⚡",
      title: "Practical & Job-Ready",
      desc: "Learn English you can use at work from Day 1."
    },
    {
      icon: "🤖",
      title: "AI-Powered Learning",
      desc: "Practice speaking, get instant feedback and improve faster."
    },
    {
      icon: "📚",
      title: "Structured & Simple",
      desc: "A clear, step-by-step learning path across 4 modules."
    },
    {
      icon: "📈",
      title: "Track & Grow",
      desc: "Measure your progress and build real confidence."
    }
  ];

  return (
    <section className="py-16 md:py-24 bg-white text-slate-900 overflow-hidden">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 lg:gap-16 items-center">
          
          {/* Left Column: Photo collage matching reference */}
          <div className="lg:col-span-5 relative flex justify-center">
            <div className="relative max-w-[400px]">
              <img 
                src="/assets/why-choose-collage.jpg" 
                alt="Why Choose Elevora Team" 
                className="w-full h-auto drop-shadow-xl rounded-3xl"
              />
            </div>
          </div>

          {/* Right Column: Why Choose Elevora details */}
          <div className="lg:col-span-7 space-y-6">
            <div>
              <h2 className="text-3xl sm:text-4xl font-extrabold text-slate-900 font-display">
                Why Choose <span className="text-[#F5A623]">Elevora?</span>
              </h2>
              <p className="mt-2 text-sm sm:text-base text-slate-600 max-w-xl">
                More than just English lessons, Elevora helps you communicate, collaborate and grow in your career.
              </p>
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-5 pt-2">
              {pillars.map((pillar, idx) => (
                <div 
                  key={idx}
                  className="flex items-start gap-3.5 p-2 rounded-2xl"
                >
                  <div className="w-10 h-10 rounded-xl bg-[#0B1528] flex items-center justify-center text-lg text-amber-400 shrink-0 shadow-md">
                    {pillar.icon}
                  </div>
                  <div>
                    <h3 className="text-sm font-bold text-slate-900 leading-tight">
                      {pillar.title}
                    </h3>
                    <p className="text-xs text-slate-500 mt-1 leading-relaxed">
                      {pillar.desc}
                    </p>
                  </div>
                </div>
              ))}
            </div>

          </div>

        </div>
      </div>
    </section>
  );
};
