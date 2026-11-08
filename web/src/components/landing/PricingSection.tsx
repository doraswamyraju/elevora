import React from 'react';
import { Check, Sparkles } from 'lucide-react';

interface PricingSectionProps {
  onSelectPlan: (planName: string) => void;
}

export const PricingSection: React.FC<PricingSectionProps> = ({ onSelectPlan }) => {
  const plans = [
    {
      name: "Basic",
      tagline: "Start your English learning journey.",
      price: "₹99",
      period: "/ month",
      isPopular: false,
      buttonText: "Get Basic",
      features: [
        "Access to core 4-module curriculum",
        "Vocabulary flashcards & audio guides",
        "End-of-day progress quizzes",
        "Mobile & Web access"
      ]
    },
    {
      name: "AI Learner",
      tagline: "Learn, practice and get 24/7 AI feedback.",
      price: "₹199",
      period: "/ month",
      isPopular: true,
      buttonText: "Get AI Learner",
      features: [
        "Everything in Basic, plus:",
        "Unlimited AI Speaking Tutor role-play",
        "Instant pronunciation & fluency feedback",
        "Personalized sentence improvements",
        "Scenario-based workplace simulator"
      ]
    },
    {
      name: "Premium",
      tagline: "Complete learning experience with trainer support.",
      price: "₹299",
      period: "/ month",
      isPopular: false,
      buttonText: "Get Premium",
      features: [
        "Everything in AI Learner, plus:",
        "Extended AI conversations & telemetry",
        "Advanced executive roleplay scenarios",
        "Weekly trainer feedback & certificate",
        "Priority 24/7 support"
      ]
    }
  ];

  return (
    <section id="pricing" className="py-20 md:py-32 relative bg-[#080C16]">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        {/* Heading */}
        <div className="text-center max-w-2xl mx-auto mb-16">
          <div className="inline-flex items-center gap-1.5 px-3 py-1 rounded-full bg-amber-500/10 border border-amber-500/25 text-amber-300 text-xs font-semibold uppercase tracking-wider mb-3">
            <span>Affordable Plans</span>
          </div>
          <h2 className="text-3xl sm:text-4xl font-extrabold text-white font-display">
            Simple & <span className="text-amber-400">Flexible Pricing</span>
          </h2>
          <p className="mt-3 text-base text-slate-300">
            Choose a plan that fits your career learning goals. Cancel anytime.
          </p>
        </div>

        {/* Pricing Cards Grid (3 cards matching reference mockup) */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 items-stretch">
          {plans.map((p, idx) => (
            <div
              key={idx}
              className={`rounded-3xl p-8 flex flex-col justify-between transition-all relative ${
                p.isPopular
                  ? 'bg-gradient-to-b from-[#131F38] via-[#0E172B] to-[#0A101D] border-2 border-amber-400 shadow-2xl shadow-amber-500/15 md:-translate-y-2'
                  : 'bg-[#0E172B] border border-slate-800 hover:border-slate-700'
              }`}
            >
              {p.isPopular && (
                <div className="absolute -top-3.5 left-1/2 -translate-x-1/2 px-4 py-1 rounded-full bg-gradient-to-r from-amber-400 to-yellow-400 text-slate-950 font-extrabold text-xs tracking-wider uppercase flex items-center gap-1 shadow-md">
                  <Sparkles className="w-3.5 h-3.5 fill-current" />
                  <span>★ Most Popular</span>
                </div>
              )}

              <div>
                <h3 className="text-xl font-bold text-white mb-1">{p.name}</h3>
                <p className="text-xs text-slate-400 min-h-[36px]">{p.tagline}</p>

                {/* Price Display */}
                <div className="my-6 pb-6 border-b border-slate-800 flex items-baseline gap-1">
                  <span className="text-4xl sm:text-5xl font-extrabold text-white tracking-tight font-display">
                    {p.price}
                  </span>
                  <span className="text-sm font-medium text-slate-400">
                    {p.period}
                  </span>
                </div>

                {/* Features List */}
                <div className="space-y-3 mb-8">
                  {p.features.map((feat, i) => (
                    <div key={i} className="flex items-start gap-2.5 text-xs sm:text-sm text-slate-300">
                      <div className="w-4 h-4 rounded-full bg-amber-400/20 text-amber-400 flex items-center justify-center shrink-0 mt-0.5">
                        <Check className="w-3 h-3 stroke-[3]" />
                      </div>
                      <span>{feat}</span>
                    </div>
                  ))}
                </div>
              </div>

              {/* Card CTA Button */}
              <button
                onClick={() => onSelectPlan(p.name)}
                className={`w-full py-3.5 rounded-xl font-bold text-sm transition-all shadow-md ${
                  p.isPopular
                    ? 'bg-gradient-to-r from-amber-400 via-yellow-400 to-amber-500 hover:from-amber-300 hover:to-amber-400 text-slate-950 shadow-amber-500/25 hover:shadow-amber-500/40'
                    : 'bg-slate-800 hover:bg-slate-700 text-white border border-slate-700'
                }`}
              >
                {p.buttonText}
              </button>
            </div>
          ))}
        </div>

      </div>
    </section>
  );
};

